package com.javalab2;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class DI {
  public void register(Class<?> newClass) throws RegistrationException {
    if (!mRegistrationState) {
      throw new RegistrationException("Registration has already performed");
    }
    if (newClass.isInterface()) {
      throw new RegistrationException("The implementation should be provided for interface");
    }
    Constructor<?>[] constructors = newClass.getConstructors();
    int amountOfInjectConstructors = 0;
    Constructor<?> injectConstructor = null;
    for (Constructor<?> constructor : constructors) {
      if (constructor.getAnnotation(Inject.class) != null) {
        injectConstructor = constructor;
        amountOfInjectConstructors += 1;
      }
      if (amountOfInjectConstructors >= 2) {
        throw new RegistrationException(
                "Multiple public inject constructors in " + newClass.getSimpleName() + " class");
      }
    }
    if (amountOfInjectConstructors == 0) {
      throw new RegistrationException("No public inject constructor founded in class " +
              newClass.getSimpleName());
    }
    mMapWithClassesAndConstructors.put(newClass, injectConstructor);
  }

  public void register(Class<?> inputInterface, Class<?> inputImplementation)
          throws RegistrationException {
    if ((!inputInterface.isInterface()) ||
            (!inputInterface.isAssignableFrom(inputImplementation))) {
      throw new RegistrationException("Interface and it's implementation expected");
    }
    mMapForInterfaces.put(inputInterface, inputImplementation);
    register(inputImplementation);
  }

  public void completeRegistration() throws RegistrationException {
    mRegistrationState = false;
    for (Map.Entry<Class<?>, Constructor<?>> pair : mMapWithClassesAndConstructors.entrySet()) {
      if (pair.getValue().isVarArgs()) {
        throw new RegistrationException("VarArgs constructors are not allowed. Class: " +
                pair.getKey().getSimpleName());
      }
      for (Class<?> parameter : pair.getValue().getParameterTypes()) {
        if (parameter.isInterface()) {
          Class<?> temp = mMapForInterfaces.get(parameter);
          if (temp != null) {
            parameter = temp;
          }
        }
        if (!mMapWithClassesAndConstructors.containsKey(parameter)) {
          throw new RegistrationException("Class " + parameter.getSimpleName() +
                  " should be used in DI, but it's not registered");
        }
      }
    }
  }

  public <T> T resolve(Class<T> inputClass) throws InvocationTargetException,
          InstantiationException, IllegalAccessException, ClassCreationException {
    if (!mMapWithClassesAndConstructors.containsKey(inputClass) &&
            !mMapForInterfaces.containsKey(inputClass)) {
      throw new ClassCreationException("Can not resolve class, which is not registered");
    }
    if (inputClass.isInterface()) {
      return inputClass.cast(resolve(mMapForInterfaces.get(inputClass)));
    }
    if (inputClass.getAnnotation(Singleton.class) != null) {
      Object temp = mMapForSingletonObjects.get(inputClass);
      if (temp != null) {
        return inputClass.cast(temp);
      }
    }
    Constructor<?> injectConstructor = mMapWithClassesAndConstructors.get(inputClass);
    Class<?>[] parameters = injectConstructor.getParameterTypes();
    Object[] tArr = new Object[parameters.length];
    for (int i = 0; i < parameters.length; i++) {
      tArr[i] = resolve(parameters[i]);
    }
    Object newClass = injectConstructor.newInstance(tArr);
    if (inputClass.getAnnotation(Singleton.class) != null) {
      mMapForSingletonObjects.put(inputClass, newClass);
    }
    return inputClass.cast(newClass);
  }

  private final HashMap<Class<?>, Class<?>> mMapForInterfaces = new HashMap<>();
  private final HashMap<Class<?>, Object> mMapForSingletonObjects = new HashMap<>();
  private final HashMap<Class<?>, Constructor<?>> mMapWithClassesAndConstructors = new HashMap<>();
  private boolean mRegistrationState = true;
}
