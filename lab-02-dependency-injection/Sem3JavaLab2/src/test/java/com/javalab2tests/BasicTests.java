package com.javalab2tests;

import com.javalab2.ClassCreationException;
import com.javalab2.DI;
import com.javalab2.RegistrationException;
import com.javalab2tests.ClassSets.ClassSet1.*;
import com.javalab2tests.ClassSets.ClassSet2.*;
import com.javalab2tests.ClassSets.ClassSet3.*;
import com.javalab2tests.ClassSets.ClassSet4.*;
import com.javalab2tests.ClassSets.ClassSet5.*;
import com.javalab2tests.ClassSets.ClassSet6.*;
import com.javalab2tests.ClassSets.ClassSet7.*;
import com.javalab2tests.ClassSets.ClassSet8.*;
import com.javalab2tests.ClassSets.ClassSet9.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

public class BasicTests {
  @Test
  public void classSet1() throws RegistrationException, InvocationTargetException,
          InstantiationException, IllegalAccessException, ClassCreationException {
    DI di = new DI();
    di.register(Class2.class);
    di.register(Class3.class);
    di.register(Class1.class);
    di.completeRegistration();
    Class1 firstClass1 = di.resolve(Class1.class);
    Class1 secondClass1 = di.resolve(Class1.class);
    Class2 firstClass2 = di.resolve(Class2.class);
    Class2 secondClass2 = di.resolve(Class2.class);
    Class2 thirdClass2 = di.resolve(Class2.class);
    Class3 firstClass3 = di.resolve(Class3.class);
    Class3 secondClass3 = di.resolve(Class3.class);
    assertNotSame(firstClass1, secondClass1);
    assertNotSame(firstClass2, secondClass2);
    assertNotSame(firstClass2, thirdClass2);
    assertNotSame(secondClass2, thirdClass2);
    assertNotSame(firstClass3, secondClass3);
  }

  @Test
  public void classSet2() throws RegistrationException, InvocationTargetException,
          InstantiationException, IllegalAccessException, ClassCreationException {
    DI di = new DI();
    di.register(SingleClass.class);
    di.completeRegistration();
    SingleClass firstSingleClass = di.resolve(SingleClass.class);
    SingleClass secondSingleClass = di.resolve(SingleClass.class);
    assertNotSame(firstSingleClass, secondSingleClass);
  }

  @Test
  public void classSet3() throws RegistrationException, InvocationTargetException,
          InstantiationException, IllegalAccessException, ClassCreationException {
    DI di = new DI();
    di.register(Dependence2.class);
    di.register(BaseClass.class);
    di.register(Dependence1.class);
    di.register(DependentOnTwoClass.class);
    di.completeRegistration();
    Dependence1 firstDependence1 = di.resolve(Dependence1.class);
    DependentOnTwoClass firstDependentOnTwoClass = di.resolve(DependentOnTwoClass.class);
    BaseClass firstBaseClass = di.resolve(BaseClass.class);
    Dependence2 firstDependence2 = di.resolve(Dependence2.class);
    Dependence1 secondDependence1 = di.resolve(Dependence1.class);
    Dependence2 secondDependence2 = di.resolve(Dependence2.class);
    DependentOnTwoClass secondDependentOnTwoClass = di.resolve(DependentOnTwoClass.class);
    BaseClass secondBaseClass = di.resolve(BaseClass.class);
    assertNotSame(firstDependence1, secondDependence1);
    assertNotSame(firstDependence2, secondDependence2);
    assertNotSame(firstBaseClass, secondBaseClass);
    assertNotSame(firstDependentOnTwoClass, secondDependentOnTwoClass);
    assertNotSame(firstBaseClass.GetDependence(), firstDependentOnTwoClass);
    assertNotSame(firstBaseClass.GetDependence(), secondDependentOnTwoClass);
    assertNotSame(firstDependentOnTwoClass.GetFirstDependence(), firstDependence1);
    assertNotSame(firstDependentOnTwoClass.GetFirstDependence(), secondDependence1);
    assertNotSame(firstDependentOnTwoClass.GetSecondDependence(), firstDependence2);
    assertNotSame(firstDependentOnTwoClass.GetSecondDependence(), secondDependence2);
  }

  @Test
  public void classSet4() throws RegistrationException, InvocationTargetException,
          InstantiationException, IllegalAccessException, ClassCreationException {
    DI di = new DI();
    di.register(A.class);
    di.register(B1.class);
    di.register(B2.class);
    di.register(B3.class);
    di.register(B4.class);
    di.register(C1.class);
    di.register(C2.class);
    di.register(C3.class);
    di.register(D1.class);
    di.completeRegistration();
    B2 b2 = di.resolve(B2.class);
    B4 b4 = di.resolve(B4.class);
    A firstA = di.resolve(A.class);
    C2 c2 = di.resolve(C2.class);
    A secondA = di.resolve(A.class);
    D1 firstD1 = di.resolve(D1.class);
    C1 c1 = di.resolve(C1.class);
    D1 secondD1 = di.resolve(D1.class);
    assertNotSame(firstA, secondA);
    assertNotSame(firstD1, secondD1);
  }

  @Test
  public void classSet5() throws RegistrationException, InvocationTargetException,
          InstantiationException, IllegalAccessException, ClassCreationException {
    DI di = new DI();
    di.register(Z.class);
    di.register(DependentOnTUD1.class);
    di.register(DependentOnTUD2.class);
    di.register(DependenceOfTUD.class);
    di.register(X.class);
    di.register(TwiceUsedDependence.class);
    di.completeRegistration();
    X firstX = di.resolve(X.class);
    X secondX = di.resolve(X.class);
    assertNotSame(firstX, secondX);
    assertNotSame(firstX.GetDeDependentOnTUD1(), secondX.GetDeDependentOnTUD1());
    assertNotSame(firstX.GetDeDependentOnTUD2(), secondX.GetDeDependentOnTUD2());
    assertNotSame(firstX.GetDeDependentOnTUD1().GetTwiceUsedDependence(),
            secondX.GetDeDependentOnTUD1().GetTwiceUsedDependence());
    assertNotSame(firstX.GetDeDependentOnTUD1().GetTwiceUsedDependence(),
            firstX.GetDeDependentOnTUD2().GetTwiceUsedDependence());
    assertNotSame(firstX.GetDeDependentOnTUD1().GetTwiceUsedDependence().GetDependenceOfTUD(),
            firstX.GetDeDependentOnTUD2().GetTwiceUsedDependence().GetDependenceOfTUD());
    Z z = di.resolve(Z.class);
    DependentOnTUD2 dependentOnTUD2 = di.resolve(DependentOnTUD2.class);
    DependentOnTUD1 dependentOnTUD1 = di.resolve(DependentOnTUD1.class);
    TwiceUsedDependence firstTwiceUsedDependence = di.resolve(TwiceUsedDependence.class);
    TwiceUsedDependence secondTwiceUsedDependence = di.resolve(TwiceUsedDependence.class);
    assertNotSame(firstTwiceUsedDependence, secondTwiceUsedDependence);
    assertNotSame(firstTwiceUsedDependence.GetDependenceOfTUD(),
            secondTwiceUsedDependence.GetDependenceOfTUD());
  }

  @Test
  public void classSet6() throws RegistrationException, InvocationTargetException,
          InstantiationException, IllegalAccessException, ClassCreationException {
    DI di = new DI();
    di.register(SeparatedSingleClass.class);
    di.register(DependenceFromTriple1.class);
    di.register(DependentFromTriple.class);
    di.register(DependentFromPair.class);
    di.register(DependenceFromTriple2.class);
    di.register(DependenceFromPair.class);
    di.completeRegistration();
    SeparatedSingleClass firstSeparatedSingleClass = di.resolve(SeparatedSingleClass.class);
    SeparatedSingleClass secondSeparatedSingleClass2 = di.resolve(SeparatedSingleClass.class);
    assertNotSame(firstSeparatedSingleClass, secondSeparatedSingleClass2);
    DependenceFromTriple1 dependenceFromTriple1 = di.resolve(DependenceFromTriple1.class);
    DependentFromTriple dependentFromTriple = di.resolve(DependentFromTriple.class);
    DependenceFromTriple2 dependenceFromTriple2 = di.resolve(DependenceFromTriple2.class);
    DependentFromPair firstDependentFromPair = di.resolve(DependentFromPair.class);
    DependenceFromPair dependenceFromPair = di.resolve(DependenceFromPair.class);
    DependentFromPair secondDependentFromPair = di.resolve(DependentFromPair.class);
    assertNotSame(firstDependentFromPair, secondDependentFromPair);
  }

  @Test
  public void classSet7() throws RegistrationException, InvocationTargetException,
          InstantiationException, IllegalAccessException, ClassCreationException {
    DI di = new DI();
    di.register(OuterClass.MiddleClass.class);
    di.register(DependenceOfInnerClass.class);
    di.register(DependenceOfMiddleClass.class);
    di.register(OuterClass.class);
    di.register(OuterClass.MiddleClass.InnerClass.class);
    di.completeRegistration();
    OuterClass.MiddleClass.InnerClass firstInnerClass =
            di.resolve(OuterClass.MiddleClass.InnerClass.class);
    OuterClass.MiddleClass.InnerClass secondInnerClass =
            di.resolve(OuterClass.MiddleClass.InnerClass.class);
    assertNotSame(firstInnerClass, secondInnerClass);
    OuterClass firstOuterClass = di.resolve(OuterClass.class);
    OuterClass secondOuterClass = di.resolve(OuterClass.class);
    assertNotSame(firstOuterClass, secondOuterClass);
    OuterClass.MiddleClass firstMiddleClass = di.resolve(OuterClass.MiddleClass.class);
    OuterClass.MiddleClass secondMiddleClass = di.resolve(OuterClass.MiddleClass.class);
    assertNotSame(firstMiddleClass, secondMiddleClass);
    DependenceOfMiddleClass firstDependenceOfMiddleClass =
            di.resolve(DependenceOfMiddleClass.class);
    DependenceOfMiddleClass secondDependenceOfMiddleClass =
            di.resolve(DependenceOfMiddleClass.class);
    assertNotSame(firstDependenceOfMiddleClass, secondDependenceOfMiddleClass);
    DependenceOfInnerClass dependenceOfInnerClass = di.resolve(DependenceOfInnerClass.class);
  }

  @Test
  public void classSet8() throws RegistrationException, InvocationTargetException,
          InstantiationException, IllegalAccessException, ClassCreationException {
    DI di = new DI();
    di.register(AnotherDependenceInterface.class, AnotherDependenceImpl.class);
    di.register(BaseDependence2.class);
    di.register(BaseDependence1Interface.class, BaseDependence1Impl.class);
    di.register(BaseInterface.class, BaseImpl.class);
    di.completeRegistration();
    AnotherDependenceImpl firstAnotherDependenceImpl = di.resolve(AnotherDependenceImpl.class);
    AnotherDependenceInterface secondAnotherDependenceImpl =
            di.resolve(AnotherDependenceInterface.class);
    AnotherDependenceImpl thirdAnotherDependenceImpl = di.resolve(AnotherDependenceImpl.class);
    assertNotSame(firstAnotherDependenceImpl, secondAnotherDependenceImpl);
    assertNotSame(firstAnotherDependenceImpl, thirdAnotherDependenceImpl);
    assertNotSame(secondAnotherDependenceImpl, thirdAnotherDependenceImpl);
    BaseInterface firstBaseImpl = di.resolve(BaseInterface.class);
    BaseInterface secondBaseImpl = di.resolve(BaseInterface.class);
    BaseImpl thirdBaseImpl = di.resolve(BaseImpl.class);
    assertNotSame(firstBaseImpl, secondBaseImpl);
    assertNotSame(firstBaseImpl, thirdBaseImpl);
    assertNotSame(secondBaseImpl, thirdBaseImpl);
    BaseDependence1Impl firstBaseDependence1Impl = di.resolve(BaseDependence1Impl.class);
    BaseDependence1Interface secondBaseDependence1Impl =
            di.resolve(BaseDependence1Interface.class);
    assertNotSame(firstBaseDependence1Impl, secondBaseDependence1Impl);
    BaseDependence2 firstBaseDependence2 = di.resolve(BaseDependence2.class);
    BaseDependence2 secondBaseDependence2 = di.resolve(BaseDependence2.class);
    assertNotSame(firstBaseDependence2, secondBaseDependence2);
  }

  @Test
  public void classSet9() throws RegistrationException, InvocationTargetException,
          InstantiationException, IllegalAccessException, ClassCreationException {
    DI di = new DI();
    di.register(BetweenSingletons2.class);
    di.register(Singleton1Interface.class, Singleton1Impl.class);
    di.register(Singleton2.class);
    di.register(Base.class);
    di.register(BetweenSingletons1.class);
    di.completeRegistration();
    di.resolve(BetweenSingletons1.class);
    BetweenSingletons1 firstBetweenSingletons1 = di.resolve(BetweenSingletons1.class);
    BetweenSingletons2 betweenSingletons2 = di.resolve(BetweenSingletons2.class);
    BetweenSingletons1 secondBetweenSingletons1 = di.resolve(BetweenSingletons1.class);
    Singleton2 singleton2 = di.resolve(Singleton2.class);
    assertNotSame(firstBetweenSingletons1, secondBetweenSingletons1);
    assertSame(firstBetweenSingletons1.GetSingleton(), singleton2);
    assertSame(secondBetweenSingletons1.GetSingleton(), singleton2);
    assertSame(betweenSingletons2.GetSingleton(), singleton2);
    Singleton1Impl firstSingleton1 = di.resolve(Singleton1Impl.class);
    Singleton1Impl secondSingleton1 = (Singleton1Impl) di.resolve(Singleton1Interface.class);
    assertSame(firstSingleton1.GetSecondDependence().GetSingleton(), singleton2);
    assertSame(secondSingleton1.GetFirstDependence().GetSingleton(), singleton2);
    assertSame(firstSingleton1, secondSingleton1);
    Base base = di.resolve(Base.class);
  }
}
