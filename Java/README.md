# Introduction to the Java Generic Matrix class `(JAGMA)`

The **Java Generic Matrix** class or **`JAGMA`**, commonly referred to as `GenericMatrix`, is a versatile Java class designed to represent a matrix of numerical values. It leverages Java's generics to support a wide range of number types, including `Integer`, `Double`, `Float`, and more, as long as they are subclasses of the `Number` class. This class provides a variety of constructors for creating matrices from different data sources, such as arrays, 2D arrays, or by specifying the number of rows and columns. Additionally, it offers a comprehensive suite of methods for performing matrix operations like addition, subtraction, multiplication, transposition, determinant calculation, and more.

# Overview of JAGMA

**`JAGMA`** is a matrix class that efficiently handles numerical data. It utilizes a one-dimensional array to store matrix elements in a row-major order, facilitating efficient data access and manipulation. The class includes functionalities for setting and retrieving elements, reshaping the matrix, flattening it into a one-dimensional array, and conducting eigenvalue decomposition. It also provides methods to check if the matrix has converged, and to verify if the matrix is symmetric and positive definite.

**`JAGMA`** is designed to work seamlessly with any subclass of the `Number` class, encompassing `Integer`, `Double`, `Float`, `Long`, and others. This design allows for broad applicability across various contexts and applications.

# Supported Data Types

The generic type **`T`** in `JAGMA` refers to any type of `Number`. This means the matrix can store elements of the following types:

- **`Byte`** (not `byte`)
- **`Short`** (not `short`)
- **`Integer`** (not `int`)
- **`Long`** (not `long`)
- **`Float`** (not `float`)
- **`Double`** (not `double`)

# Getting Started with JAGMA

## Step 1: Importing the Matrix Class

First, import the `Matrix` class into your Java file. If the class is part of a package, include the package name in the import statement. For instance:

```java
import ci.abidjan.adi.JAGMA;
```

Replace `mypackage` with the actual package name where the `Matrix` class is located.

## Step 2: Creating a Matrix Instance

Create a new `Matrix` object using one of the available constructors. Here are a few examples:

- Initialize a 3x3 identity matrix filled with zeros:

```java
Matrix<Double> mat3x3 = new Matrix<>(3, 3, () -> 0.0);
```

- Initialize a matrix using an existing 2D array:

```java
double[][] initData = {{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}, {7.0, 8.0, 9.0}};
Matrix<Double> matFromArray = new Matrix<>(initData);
```

- Initialize a matrix using a List of Lists containing numbers:

```java
List<List<Double>> listOfLists = new ArrayList<>();
listOfLists.add(Collections.singletonList(1.0));
listOfLists.add(Arrays.asList(2.0, 3.0));
listOfLists.add(Arrays.asList(4.0, 5.0, 6.0));
Matrix<Double> matFromList = new Matrix<>(listOfLists);
```

## Step 3: Accessing and Modifying Elements

Access individual elements using their row and column indices:

```java
System.out.printf("%.2f%n", mat3x3.get(0, 0)); // Output: 0.00
```

Set element values using the setter method:

```java
mat3x3.set(0, 0, 1.0);
System.out.printf("%.2f%n", mat3x3.get(0, 0)); // Output: 1.00
```

## Step 4: Performing Basic Arithmetic Operations

Execute simple arithmetic operations such as addition, subtraction, and scalar multiplication:

```java
Matrix<Double> matAdd = mat3x3.add(matFromList);
Matrix<Double> matSub = mat3x3.subtract(matFromList);
Matrix<Double> matMulScalar = mat3x3.multiply(2.0);
```

## Step 5: Transposition, Determinant, and Inverse

Transpose a matrix, calculate the determinant, and find the inverse of a square matrix:

```java
Matrix<Double> matTransposed = mat3x3.transpose();

if (mat3x3.isSquare()) {
   double det = mat3x3.determinant();
   Matrix<Double> inv = mat3x3.inverse();
}
```

## Step 6: Matrix Multiplication

Multiply two matrices together using either the standard ijk algorithm or the faster IKJ algorithm:

```java
Matrix<Double> matMultiplicationIjk = mat3x3.ikjalgorithm1D(matFromList);
Matrix<Double> matMultiplicationStandard = mat3x3.mult(matFromList);
```

## Step 7: Printing Matrices

Print out the contents of a matrix using built-in methods or customized string representation:

```java
mat3x3.printAsMatrix();
mat3x3.printAsMatlab();
```

These steps cover the basics of using the `Matrix` class. You can explore additional functionalities such as partitioning, diagonalization, eigenvalues, and more, as per your requirements.

# Future Enhancements

Stay tuned for more functionalities and methods concerning matrix operations to be added to the Java Generic Matrix class. These enhancements will further expand the capabilities and utility of `JAGMA`.
