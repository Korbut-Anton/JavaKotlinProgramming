package com.javalab2tests;

import com.javalab2.ClassCreationException;
import com.javalab2.DI;
import com.javalab2.RegistrationException;
import com.javalab2tests.ClassSets.ClassSet2.*;
import com.javalab2tests.ClassSets.ClassSet4.*;
import com.javalab2tests.ClassSets.ClassSet6.*;
import com.javalab2tests.ClassSets.ClassSet8.*;
import com.javalab2tests.ClassSets.ClassSet10.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExceptionTests {
  @Test
  public void RegistrationStateTest() throws RegistrationException {
    DI di1 = new DI();
    di1.register(SingleClass.class);
    di1.completeRegistration();
    RegistrationException thrown1 = assertThrows(RegistrationException.class, () ->
            di1.register(A.class));
    assertEquals(thrown1.getMessage(), "Registration has already performed");
    DI di2 = new DI();
    di2.register(AnotherDependenceImpl.class);
    di2.completeRegistration();
    RegistrationException thrown2 = assertThrows(RegistrationException.class,
            () -> di2.register(BaseDependence1Interface.class, BaseDependence1Impl.class));
    assertEquals(thrown2.getMessage(), "Registration has already performed");
  }

  @Test
  public void InterfaceWithoutImplTest() {
    DI di = new DI();
    RegistrationException thrown = assertThrows(RegistrationException.class, () ->
            di.register(BaseInterface.class));
    assertEquals(thrown.getMessage(), "The implementation should be provided for interface");
  }

  @Test
  public void AmountOfInjectConstructorsTest() throws RegistrationException {
    DI di1 = new DI();
    di1.register(ManyInjectConstructorsClass2.class);
    RegistrationException thrown1 = assertThrows(RegistrationException.class, () ->
            di1.register(ManyInjectConstructorsClass1.class));
    assertEquals(thrown1.getMessage(), "Multiple public inject constructors in " +
            "ManyInjectConstructorsClass1 class");
    DI di2 = new DI();
    RegistrationException thrown2 = assertThrows(RegistrationException.class, () ->
            di2.register(NoPublicInjectConstructorsClass.class));
    assertEquals(thrown2.getMessage(), "No public inject constructor founded in class " +
            "NoPublicInjectConstructorsClass");
  }

  @Test
  public void InterfaceAndImplRegistrationTest() {
    DI di1 = new DI();
    RegistrationException thrown1 = assertThrows(RegistrationException.class, () ->
            di1.register(AnotherDependenceInterface.class, BaseDependence1Impl.class));
    assertEquals(thrown1.getMessage(), "Interface and it's implementation expected");
    DI di2 = new DI();
    RegistrationException thrown2 = assertThrows(RegistrationException.class, () ->
            di2.register(BaseDependence2.class, BaseDependence1Impl.class));
    assertEquals(thrown1.getMessage(), "Interface and it's implementation expected");
    DI di3 = new DI();
    RegistrationException thrown3 = assertThrows(RegistrationException.class, () ->
            di3.register(BaseInterface.class, BaseDependence1Interface.class));
    assertEquals(thrown1.getMessage(), "Interface and it's implementation expected");
  }

  @Test
  public void VarArgConstructorTest() throws RegistrationException {
    DI di = new DI();
    di.register(VarArgConstructorClass.class);
    RegistrationException thrown = assertThrows(RegistrationException.class,
            di::completeRegistration);
    assertEquals(thrown.getMessage(), "VarArgs constructors are not allowed. Class: " +
            "VarArgConstructorClass");
  }

  @Test
  public void IncompleteRegistrationTest() throws RegistrationException {
    DI di1 = new DI();
    di1.register(DependentFromPair.class);
    RegistrationException thrown1 = assertThrows(RegistrationException.class,
            di1::completeRegistration);
    assertEquals(thrown1.getMessage(), "Class DependenceFromPair should be used in DI, " +
            "but it's not registered");
    DI di2 = new DI();
    di2.register(AnotherDependenceInterface.class, AnotherDependenceImpl.class);
    di2.register(BaseDependence2.class);
    di2.register(BaseDependence1Impl.class);
    di2.register(BaseInterface.class, BaseImpl.class);
    RegistrationException thrown2 = assertThrows(RegistrationException.class,
            di2::completeRegistration);
    assertEquals(thrown2.getMessage(), "Class BaseDependence1Interface should be used in DI, " +
            "but it's not registered");
  }

  @Test
  public void ResolvingNonRegisteredClassTest() throws RegistrationException {
    DI di = new DI();
    di.register(DependenceFromTriple1.class);
    di.register(DependenceFromTriple2.class);
    di.register(DependentFromTriple.class);
    di.completeRegistration();
    ClassCreationException thrown = assertThrows(ClassCreationException.class, () ->
            di.resolve(A.class));
    assertEquals(thrown.getMessage(), "Can not resolve class, which is not registered");
  }
}
