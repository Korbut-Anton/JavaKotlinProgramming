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
    Class1 firstClass1 = (Class1) di.resolve(Class1.class);
    Class1 secondClass1 = (Class1) di.resolve(Class1.class);
    Class2 firstClass2 = (Class2) di.resolve(Class2.class);
    Class2 secondClass2 = (Class2) di.resolve(Class2.class);
    Class2 thirdClass2 = (Class2) di.resolve(Class2.class);
    Class3 firstClass3 = (Class3) di.resolve(Class3.class);
    Class3 secondClass3 = (Class3) di.resolve(Class3.class);
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
    SingleClass firstSingleClass = (SingleClass) di.resolve(SingleClass.class);
    SingleClass secondSingleClass = (SingleClass) di.resolve(SingleClass.class);
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
    Dependence1 firstDependence1 = (Dependence1) di.resolve(Dependence1.class);
    DependentOnTwoClass firstDependentOnTwoClass = (DependentOnTwoClass)
            di.resolve(DependentOnTwoClass.class);
    BaseClass firstBaseClass = (BaseClass) di.resolve(BaseClass.class);
    Dependence2 firstDependence2 = (Dependence2) di.resolve(Dependence2.class);
    Dependence1 secondDependence1 = (Dependence1) di.resolve(Dependence1.class);
    Dependence2 secondDependence2 = (Dependence2) di.resolve(Dependence2.class);
    DependentOnTwoClass secondDependentOnTwoClass = (DependentOnTwoClass)
            di.resolve(DependentOnTwoClass.class);
    BaseClass secondBaseClass = (BaseClass) di.resolve(BaseClass.class);
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
    B2 b2 = (B2) di.resolve(B2.class);
    B4 b4 = (B4) di.resolve(B4.class);
    A firstA = (A) di.resolve(A.class);
    C2 c2 = (C2) di.resolve(C2.class);
    A secondA = (A) di.resolve(A.class);
    D1 firstD1 = (D1) di.resolve(D1.class);
    C1 c1 = (C1) di.resolve(C1.class);
    D1 secondD1 = (D1) di.resolve(D1.class);
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
    X firstX = (X) di.resolve(X.class);
    X secondX = (X) di.resolve(X.class);
    assertNotSame(firstX, secondX);
    assertNotSame(firstX.GetDeDependentOnTUD1(), secondX.GetDeDependentOnTUD1());
    assertNotSame(firstX.GetDeDependentOnTUD2(), secondX.GetDeDependentOnTUD2());
    assertNotSame(firstX.GetDeDependentOnTUD1().GetTwiceUsedDependence(),
            secondX.GetDeDependentOnTUD1().GetTwiceUsedDependence());
    assertNotSame(firstX.GetDeDependentOnTUD1().GetTwiceUsedDependence(),
            firstX.GetDeDependentOnTUD2().GetTwiceUsedDependence());
    assertNotSame(firstX.GetDeDependentOnTUD1().GetTwiceUsedDependence().GetDependenceOfTUD(),
            firstX.GetDeDependentOnTUD2().GetTwiceUsedDependence().GetDependenceOfTUD());
    Z z = (Z) di.resolve(Z.class);
    DependentOnTUD2 dependentOnTUD2 = (DependentOnTUD2) di.resolve(DependentOnTUD2.class);
    DependentOnTUD1 dependentOnTUD1 = (DependentOnTUD1) di.resolve(DependentOnTUD1.class);
    TwiceUsedDependence firstTwiceUsedDependence = (TwiceUsedDependence)
            di.resolve(TwiceUsedDependence.class);
    TwiceUsedDependence secondTwiceUsedDependence = (TwiceUsedDependence)
            di.resolve(TwiceUsedDependence.class);
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
    SeparatedSingleClass firstSeparatedSingleClass = (SeparatedSingleClass)
            di.resolve(SeparatedSingleClass.class);
    SeparatedSingleClass secondSeparatedSingleClass2 = (SeparatedSingleClass)
            di.resolve(SeparatedSingleClass.class);
    assertNotSame(firstSeparatedSingleClass, secondSeparatedSingleClass2);
    DependenceFromTriple1 dependenceFromTriple1 = (DependenceFromTriple1)
            di.resolve(DependenceFromTriple1.class);
    DependentFromTriple dependentFromTriple = (DependentFromTriple)
            di.resolve(DependentFromTriple.class);
    DependenceFromTriple2 dependenceFromTriple2 = (DependenceFromTriple2)
            di.resolve(DependenceFromTriple2.class);
    DependentFromPair firstDependentFromPair = (DependentFromPair)
            di.resolve(DependentFromPair.class);
    DependenceFromPair dependenceFromPair = (DependenceFromPair)
            di.resolve(DependenceFromPair.class);
    DependentFromPair secondDependentFromPair = (DependentFromPair)
            di.resolve(DependentFromPair.class);
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
    OuterClass.MiddleClass.InnerClass firstInnerClass = (OuterClass.MiddleClass.InnerClass)
            di.resolve(OuterClass.MiddleClass.InnerClass.class);
    OuterClass.MiddleClass.InnerClass secondInnerClass = (OuterClass.MiddleClass.InnerClass)
            di.resolve(OuterClass.MiddleClass.InnerClass.class);
    assertNotSame(firstInnerClass, secondInnerClass);
    OuterClass firstOuterClass = (OuterClass) di.resolve(OuterClass.class);
    OuterClass secondOuterClass = (OuterClass) di.resolve(OuterClass.class);
    assertNotSame(firstOuterClass, secondOuterClass);
    OuterClass.MiddleClass firstMiddleClass = (OuterClass.MiddleClass)
            di.resolve(OuterClass.MiddleClass.class);
    OuterClass.MiddleClass secondMiddleClass = (OuterClass.MiddleClass)
            di.resolve(OuterClass.MiddleClass.class);
    assertNotSame(firstMiddleClass, secondMiddleClass);
    DependenceOfMiddleClass firstDependenceOfMiddleClass = (DependenceOfMiddleClass)
            di.resolve(DependenceOfMiddleClass.class);
    DependenceOfMiddleClass secondDependenceOfMiddleClass = (DependenceOfMiddleClass)
            di.resolve(DependenceOfMiddleClass.class);
    assertNotSame(firstDependenceOfMiddleClass, secondDependenceOfMiddleClass);
    DependenceOfInnerClass dependenceOfInnerClass = (DependenceOfInnerClass)
            di.resolve(DependenceOfInnerClass.class);
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
    AnotherDependenceImpl firstAnotherDependenceImpl = (AnotherDependenceImpl)
            di.resolve(AnotherDependenceImpl.class);
    AnotherDependenceImpl secondAnotherDependenceImpl = (AnotherDependenceImpl)
            di.resolve(AnotherDependenceInterface.class);
    AnotherDependenceImpl thirdAnotherDependenceImpl = (AnotherDependenceImpl)
            di.resolve(AnotherDependenceImpl.class);
    assertNotSame(firstAnotherDependenceImpl, secondAnotherDependenceImpl);
    assertNotSame(firstAnotherDependenceImpl, thirdAnotherDependenceImpl);
    assertNotSame(secondAnotherDependenceImpl, thirdAnotherDependenceImpl);
    BaseImpl firstBaseImpl = (BaseImpl) di.resolve(BaseInterface.class);
    BaseImpl secondBaseImpl = (BaseImpl) di.resolve(BaseInterface.class);
    BaseImpl thirdBaseImpl = (BaseImpl) di.resolve(BaseImpl.class);
    assertNotSame(firstBaseImpl, secondBaseImpl);
    assertNotSame(firstBaseImpl, thirdBaseImpl);
    assertNotSame(secondBaseImpl, thirdBaseImpl);
    BaseDependence1Impl firstBaseDependence1Impl = (BaseDependence1Impl)
            di.resolve(BaseDependence1Impl.class);
    BaseDependence1Impl secondBaseDependence1Impl = (BaseDependence1Impl)
            di.resolve(BaseDependence1Interface.class);
    assertNotSame(firstBaseDependence1Impl, secondBaseDependence1Impl);
    BaseDependence2 firstBaseDependence2 = (BaseDependence2) di.resolve(BaseDependence2.class);
    BaseDependence2 secondBaseDependence2 = (BaseDependence2) di.resolve(BaseDependence2.class);
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
    BetweenSingletons1 firstBetweenSingletons1 = (BetweenSingletons1)
            di.resolve(BetweenSingletons1.class);
    BetweenSingletons2 betweenSingletons2 = (BetweenSingletons2)
            di.resolve(BetweenSingletons2.class);
    BetweenSingletons1 secondBetweenSingletons1 = (BetweenSingletons1)
            di.resolve(BetweenSingletons1.class);
    Singleton2 singleton2 = (Singleton2) di.resolve(Singleton2.class);
    assertNotSame(firstBetweenSingletons1, secondBetweenSingletons1);
    assertSame(firstBetweenSingletons1.GetSingleton(), singleton2);
    assertSame(secondBetweenSingletons1.GetSingleton(), singleton2);
    assertSame(betweenSingletons2.GetSingleton(), singleton2);
    Singleton1Impl firstSingleton1 = (Singleton1Impl) di.resolve(Singleton1Impl.class);
    Singleton1Impl secondSingleton1 = (Singleton1Impl) di.resolve(Singleton1Interface.class);
    assertSame(firstSingleton1.GetSecondDependence().GetSingleton(), singleton2);
    assertSame(secondSingleton1.GetFirstDependence().GetSingleton(), singleton2);
    assertSame(firstSingleton1, secondSingleton1);
    Base base = (Base) di.resolve(Base.class);
  }
}
