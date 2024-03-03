# Introduction to the Java Generic Matrix Class `(JAGMA)`

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

# Documentation or Get started

Matrix         var evenRowColor = "even-row-color"; var oddRowColor = "odd-row-color"; var tableTab = "table-tab"; var activeTableTab = "active-table-tab"; var pathtoroot = "../../../../../"; loadScripts(document, 'script');

JavaScript is disabled on your browser.

[Skip navigation links](#skip-navbar-top "Skip navigation links")

*   [Package](package-summary.html)
*   Class
*   [Use](class-use/Matrix.html)
*   [Tree](package-tree.html)
*   [Index](../../../../../index-files/index-1.html)
*   [Help](../../../../../help-doc.html#class)

*   Summary: 
*   Nested | 
*   Field | 
*   [Constr](#constructor-summary) | 
*   [Method](#method-summary)

*   Detail: 
*   Field | 
*   [Constr](#constructor-detail) | 
*   [Method](#method-detail)

SEARCH:  

Package [ci.abidjan.adi.core.JAGMA](package-summary.html)

Class Matrix<T extends [Number](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Number.html "class or interface in java.lang")\>
=======================================================================================================================================================

[java.lang.Object](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Object.html "class or interface in java.lang")

ci.abidjan.adi.core.JAGMA.Matrix<T>

Type Parameters:

`T` - Type parameter representing the element type (should extend Number).

* * *

public class Matrix<T extends [Number](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Number.html "class or interface in java.lang")\> extends [Object](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Object.html "class or interface in java.lang")

The Java Generic Matrix Class also known as JAGMA is a matrix class that supports integers, floats, and doubles. It represents a dense rectangular matrix with generic element type T. This implementation uses a contiguous block of memory to store all elements (1D array for performance).

*   Constructor Summary
    -------------------
    
    Constructors
    
    Constructor
    
    Description
    
    `[Matrix](#%3Cinit%3E())()`
    
    Default constructor for the Matrix class.
    
    `[Matrix](#%3Cinit%3E(int,int))(int nrows, int ncols)`
    
    Constructor to create a null matrix (initialized to 0) with specified dimensions.
    
    `[Matrix](#%3Cinit%3E(int,int,T))(int nrows, int ncols, [T](Matrix.html "type parameter in Matrix") value)`
    
    Constructor.
    
    `[Matrix](#%3Cinit%3E(int,int,T%5B%5D))(int nrows, int ncols, [T](Matrix.html "type parameter in Matrix")[] array)`
    
    Constructor.
    
    `[Matrix](#%3Cinit%3E(int,T%5B%5D))(int mcols, [T](Matrix.html "type parameter in Matrix")[] array1D)`
    
    Constructor to create a matrix from a 1D array by specifying the number of columns only.
    
    `[Matrix](#%3Cinit%3E(T%5B%5D%5B%5D))([T](Matrix.html "type parameter in Matrix")[][] array2D)`
    
    Constructor to create a matrix from a 2D array by copying its elements.
    
    `[Matrix](#%3Cinit%3E(T%5B%5D,int))([T](Matrix.html "type parameter in Matrix")[] array1D, int mrows)`
    
    Constructor to create a matrix from a 1D array by specifying the number of rows only.
    
*   Method Summary
    --------------
    
    All MethodsInstance MethodsConcrete Methods
    
    Modifier and Type
    
    Method
    
    Description
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[adjugate](#adjugate())()`
    
    Calculates the adjugate of the matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[backSubstitution](#backSubstitution(ci.abidjan.adi.core.JAGMA.Matrix,ci.abidjan.adi.core.JAGMA.Matrix))([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")> A, [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")> b)`
    
    Performs back substitution on the given upper triangular matrix and right-hand side vector.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[choleskyDecomposition](#choleskyDecomposition())()`
    
    Performs Cholesky decomposition on the matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[choleskyLDecomposition](#choleskyLDecomposition())()`
    
    Performs Cholesky L (lower triangular) decomposition on the current matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[clone](#clone())()`
    
    Clones (or makes a copy of) the current matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[comatrix](#comatrix())()`
    
    Computes the cofactor matrix (also known as the comatrix) of the matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[concatenateHorizontally](#concatenateHorizontally(ci.abidjan.adi.core.JAGMA.Matrix...))([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>... matrices)`
    
    Concatenates multiple matrices horizontally.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[concatenateVertically](#concatenateVertically(ci.abidjan.adi.core.JAGMA.Matrix...))([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>... matrices)`
    
    Concatenates multiple matrices vertically.
    
    `double`
    
    `[conditionNumber](#conditionNumber())()`
    
    Calculates the condition number of the matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[conjugate](#conjugate())()`
    
    Returns the conjugate of this matrix.
    
    `boolean`
    
    `[converged](#converged(java.lang.Number%5B%5D%5B%5D,double))([Number](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Number.html "class or interface in java.lang")[][] M, double tolerance)`
    
    Checks matrix convergence using off-diagonal element norm as tolerance.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[deleteColumns](#deleteColumns(int...))(int... indices)`
    
    Deletes specific columns from the matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[deleteColumnsRange](#deleteColumnsRange(int,int))(int colstart, int colend)`
    
    Deletes a range of columns from the matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[deleteRows](#deleteRows(int...))(int... indices)`
    
    Deletes specific rows from the matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[deleteRowsRange](#deleteRowsRange(int,int))(int rowstart, int rowend)`
    
    Deletes a range of rows from the matrix.
    
    `double`
    
    `[determinant](#determinant())()`
    
    Calculates the determinant of a square matrix using the minor() method for computation.
    
    `double`
    
    `[determinant2](#determinant2())()`
    
    Calculates the determinant of a square matrix without dependency on the minor() method.
    
    `[T](Matrix.html "type parameter in Matrix")`
    
    `[determinantByHouseholderQR](#determinantByHouseholderQR())()`
    
    Calculates the determinant of the matrix using the Householder QR decomposition method.
    
    `[T](Matrix.html "type parameter in Matrix")`
    
    `[determinantByQRGivens](#determinantByQRGivens())()`
    
    Calculates the determinant of the matrix using the Givens QR decomposition method.
    
    `[T](Matrix.html "type parameter in Matrix")`
    
    `[determinantByQRGramSchmidt](#determinantByQRGramSchmidt())()`
    
    Calculates the determinant of the matrix using the Gram-Schmidt QR decomposition method.
    
    `[T](Matrix.html "type parameter in Matrix")`
    
    `[determinantLU](#determinantLU())()`
    
    Calculates the determinant of the matrix using LU decomposition.
    
    `[T](Matrix.html "type parameter in Matrix")[]`
    
    `[diag](#diag())()`
    
    Retrieves the diagonal elements of the matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[divElementByConstant](#divElementByConstant(T))([T](Matrix.html "type parameter in Matrix") scalar)`
    
    Divides each element of the current matrix by a scalar value.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[divElementByElement](#divElementByElement(ci.abidjan.adi.core.JAGMA.Matrix))([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")> B)`
    
    Divides each element of the current matrix by the corresponding element of another matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[dotProduct](#dotProduct(ci.abidjan.adi.core.JAGMA.Matrix))([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")> vector)`
    
    Computes the dot product (inner product) of the current matrix and a vector.
    
    `[T](Matrix.html "type parameter in Matrix")`
    
    `[dotProduct](#dotProduct(ci.abidjan.adi.core.JAGMA.Matrix,ci.abidjan.adi.core.JAGMA.Matrix))([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")> vector1, [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")> vector2)`
    
    Calculates the dot product between two column vectors of the same length.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[dotProduct](#dotProduct(T))([T](Matrix.html "type parameter in Matrix") scalar)`
    
    Multiplies each element of this matrix by the specified scalar value.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[eigenvalueDecomposition](#eigenvalueDecomposition())()`
    
    Performs the Jacobi eigenvalues decomposition iterative and basic algorithm.
    
    `[T](Matrix.html "type parameter in Matrix")[]`
    
    `[eigenvalues](#eigenvalues())()`
    
    Computes the eigenvalues of the matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[eigenvectors](#eigenvectors())()`
    
    Computes the eigenvectors of the matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[elementWiseAdd](#elementWiseAdd(ci.abidjan.adi.core.JAGMA.Matrix))([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")> other)`
    
    Performs element-wise addition of the current matrix by another matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[elementWiseDivide](#elementWiseDivide(ci.abidjan.adi.core.JAGMA.Matrix))([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")> other)`
    
    Performs element-wise division of the current matrix by another matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[elementWiseMultiply](#elementWiseMultiply(ci.abidjan.adi.core.JAGMA.Matrix))([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")> other)`
    
    Performs element-wise multiplication of the current matrix by another matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[elementWiseSubtract](#elementWiseSubtract(ci.abidjan.adi.core.JAGMA.Matrix))([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")> other)`
    
    Performs element-wise subtraction of the current matrix by another matrix.
    
    `boolean`
    
    `[equals](#equals(java.lang.Object))([Object](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Object.html "class or interface in java.lang") o)`
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[extract](#extract(int,int,int,int))(int rowstart, int rowend, int colstart, int colend)`
    
    Extracts a subMatrix from the matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[extractCol](#extractCol(int))(int i)`
    
    Extracts a specific column from the matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[extractColumnsRange](#extractColumnsRange(int,int))(int from, int to)`
    
    Extracts a range of columns from the matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[extractRow](#extractRow(int))(int i)`
    
    Extracts a specific row from the matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[extractRowsRange](#extractRowsRange(int,int))(int from, int to)`
    
    Extracts a range of rows from the matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[eye](#eye(int))(int size)`
    
    Creates a square identity matrix of a given size.
    
    `final [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[fasttranspose](#fasttranspose())()`
    
    Performs the transpose of the matrix in an optimized way.
    
    `[T](Matrix.html "type parameter in Matrix")[]`
    
    `[flatten](#flatten())()`
    
    Flattens the matrix into a 1D array.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[forwardSubstitution](#forwardSubstitution(ci.abidjan.adi.core.JAGMA.Matrix,ci.abidjan.adi.core.JAGMA.Matrix))([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")> A, [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")> b)`
    
    Performs forward substitution on the given lower triangular matrix and right-hand side vector.
    
    `double`
    
    `[frobeniusNorm](#frobeniusNorm())()`
    
    Computes the Frobenius norm of the matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[generateBinaryMatrix](#generateBinaryMatrix(int,int))(int nrows, int ncols)`
    
    Generates a binary matrix filled with 0s and 1s.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[generateRandomMatrix](#generateRandomMatrix(int,int))(int nrows, int ncols)`
    
    Generates a matrix filled with random values.
    
    `[T](Matrix.html "type parameter in Matrix")`
    
    `[get](#get(int))(int i)`
    
    Retrieves the value of a specific element in the matrix's internal 1D array.
    
    `[T](Matrix.html "type parameter in Matrix")`
    
    `[get](#get(int,int))(int row, int col)`
    
    Retrieves the value of a specific element in the matrix.
    
    `[T](Matrix.html "type parameter in Matrix")[]`
    
    `[getArray](#getArray())()`
    
    Retrieves the internal array representing the matrix in 1D format.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[getColumn](#getColumn(int))(int col)`
    
    Returns the specified column of the matrix as a new matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[getConjugateTranspose](#getConjugateTranspose())()`
    
    Returns the conjugate transpose of the matrix.
    
    `int`
    
    `[getNcols](#getNcols())()`
    
    Retrieves the number of columns in the matrix.
    
    `int`
    
    `[getNrows](#getNrows())()`
    
    Retrieves the number of rows in the matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[getRow](#getRow(int))(int row)`
    
    Returns the specified row of the matrix as a new matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[getSubMatrix](#getSubMatrix(int))(int endRow)`
    
    Returns a subMatrix of the current matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[getSubMatrix](#getSubMatrix(int,int))(int i, int j)`
    
    Returns a submatrix of this matrix, excluding the specified row and column.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[getSubMatrix](#getSubMatrix(int,int,int,int))(int rowstart, int rowend, int colstart, int colend)`
    
    Extracts a subMatrix from the original matrix, using specified start and end indices for rows and columns.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[identity](#identity(int))(int size)`
    
    Creates an identity matrix with the given size.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[identity](#identity(int,int))(int nrows, int ncols)`
    
    Creates an identity matrix with the given number of rows and columns.
    
    `double`
    
    `[infinityNorm](#infinityNorm())()`
    
    Computes the infinity-norm of the matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[insertAfter](#insertAfter(int,T%5B%5D%5B%5D...))(int indexAfter, [T](Matrix.html "type parameter in Matrix")[][]... y)`
    
    Inserts rows after a specified index in the matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[insertColumnsAfter](#insertColumnsAfter(int,T%5B%5D%5B%5D...))(int indexAfter, [T](Matrix.html "type parameter in Matrix")[][]... y)`
    
    Inserts columns after a specified index in the matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[insertColumnsBefore](#insertColumnsBefore(int,T%5B%5D...))(int indexBefore, [T](Matrix.html "type parameter in Matrix")[]... y)`
    
    Inserts columns before a specified index in the matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[insertColumnsBefore](#insertColumnsBefore(int,T%5B%5D%5B%5D...))(int indexBefore, [T](Matrix.html "type parameter in Matrix")[][]... y)`
    
    Inserts columns before a specified index in the matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[insertRowsBefore](#insertRowsBefore(int,T%5B%5D...))(int indexBefore, [T](Matrix.html "type parameter in Matrix")[]... y)`
    
    Inserts rows before a specified index in the matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[insertRowsBefore](#insertRowsBefore(int,T%5B%5D%5B%5D...))(int indexBefore, [T](Matrix.html "type parameter in Matrix")[][]... y)`
    
    Inserts rows before a specified index in the matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[insertValuesBefore](#insertValuesBefore(int,T...))(int indexBefore, [T](Matrix.html "type parameter in Matrix")... y)`
    
    Inserts values before a specified index in the matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[inverse](#inverse())()`
    
    Calculates the inverse of the matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[inverseGaussJordan](#inverseGaussJordan())()`
    
    Inverts the matrix using the Gauss-Jordan elimination method.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[InverseLU](#InverseLU())()`
    
    Calculates the inverse of the matrix using LU decomposition.
    
    `boolean`
    
    `[isColumnSpaceOnly](#isColumnSpaceOnly())()`
    
    Method.
    
    `boolean`
    
    `[isDiagonal](#isDiagonal())()`
    
    Method.
    
    `boolean`
    
    `[isDiagonal2](#isDiagonal2())()`
    
    Method.
    
    `boolean`
    
    `[isEmpty](#isEmpty())()`
    
    Method.
    
    `boolean`
    
    `[isFullRank](#isFullRank())()`
    
    Method .Return true if the matrix is full rank, false otherwise.
    
    `boolean`
    
    `[isHermitian](#isHermitian())()`
    
    Method.
    
    `boolean`
    
    `[isIdentity](#isIdentity())()`
    
    Method.
    
    `boolean`
    
    `[isIdentity2](#isIdentity2())()`
    
    Method.
    
    `boolean`
    
    `[isLinearlyDependent](#isLinearlyDependent())()`
    
    Method.
    
    `boolean`
    
    `[isLinearlyIndependent](#isLinearlyIndependent())()`
    
    Method.
    
    `boolean`
    
    `[isLowerTriangular](#isLowerTriangular())()`
    
    Method.
    
    `boolean`
    
    `[isLowerTriangular2](#isLowerTriangular2())()`
    
    Method.
    
    `boolean`
    
    `[isMetricPositiveDefined](#isMetricPositiveDefined())()`
    
    Method.
    
    `boolean`
    
    `[isNonsingular](#isNonsingular())()`
    
    Method.
    
    `boolean`
    
    `[isNormal](#isNormal())()`
    
    Method.
    
    `boolean`
    
    `[isOrthogonal](#isOrthogonal())()`
    
    Method.
    
    `boolean`
    
    `[isOrthogonal2](#isOrthogonal2())()`
    
    Method.
    
    `boolean`
    
    `[isPositiveDefinite](#isPositiveDefinite())()`
    
    Method.
    
    `boolean`
    
    `[isRegular](#isRegular())()`
    
    Checks if the matrix is regular (non-singular).
    
    `boolean`
    
    `[isRowSpaceOnly](#isRowSpaceOnly())()`
    
    Method.
    
    `boolean`
    
    `[isSingular](#isSingular())()`
    
    Method.
    
    `boolean`
    
    `[isSquare](#isSquare())()`
    
    Method.
    
    `boolean`
    
    `[isSymmetric](#isSymmetric())()`
    
    Method.
    
    `boolean`
    
    `[isSymmetricPositiveDefined](#isSymmetricPositiveDefined())()`
    
    Checks if the matrix is symmetric and positive definite.
    
    `boolean`
    
    `[isUpperTriangular](#isUpperTriangular())()`
    
    Method.
    
    `boolean`
    
    `[isUpperTriangular2](#isUpperTriangular2())()`
    
    Method.
    
    `double`
    
    `[kyFanNorm](#kyFanNorm(int))(int k)`
    
    Calculates the Ky Fan k-norm of the matrix.
    
    `double`
    
    `[l1Norm](#l1Norm())()`
    
    Computes the l1-norm (Manhattan norm) of the matrix.
    
    `double`
    
    `[l2Norm](#l2Norm())()`
    
    Computes the l2-norm (Euclidean norm) of the matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[lowerTriangular](#lowerTriangular(ci.abidjan.adi.core.JAGMA.Matrix))([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")> B)`
    
    Converts the given matrix into a lower (inferior) triangular matrix.
    
    `double`
    
    `[lpNorm](#lpNorm(int))(int p)`
    
    Computes the lp-norm of the matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>[]`
    
    `[luDecomposition](#luDecomposition())()`
    
    Performs LU Decomposition on the current matrix.
    
    `double`
    
    `[maxAbsValue](#maxAbsValue())()`
    
    Computes the maximum absolute value of the matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[minor](#minor(int,int))(int r, int c)`
    
    Calculates the minor of a matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[minus](#minus(ci.abidjan.adi.core.JAGMA.Matrix))([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")> B)`
    
    Subtracts another matrix from the current matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[minus](#minus(T))([T](Matrix.html "type parameter in Matrix") scalarK)`
    
    Subtracts a scalar from each element of the matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[multiply](#multiply(ci.abidjan.adi.core.JAGMA.Matrix))([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")> B)`
    
    Multiplies the current matrix by another matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[normalize](#normalize(ci.abidjan.adi.core.JAGMA.Matrix))([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")> column)`
    
    Normalizes a column vector.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[ones](#ones(int,int))(int nrows, int ncols)`
    
    Creates a matrix with the given number of rows and columns, where all elements are ones.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[oppose](#oppose())()`
    
    Computes the opposite (negation) of each element in the matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>[][]`
    
    `[partition](#partition())()`
    
    Partitions this matrix into four equal-sized sub-matrices.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>[][]`
    
    `[partition](#partition(ci.abidjan.adi.core.JAGMA.Matrix))([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")> B)`
    
    Partitions the given matrix into four equal-sized sub-matrices.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[permutationMatrix](#permutationMatrix())()`
    
    Creates a permutation matrix of the same size as the current matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[permutationMatrix](#permutationMatrix(int))(int size)`
    
    Creates a permutation matrix of the given size.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[permutationMatrix](#permutationMatrix(int,int))(int nrows, int ncols)`
    
    Creates a permutation matrix of the given dimensions.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[permutationMatrix](#permutationMatrix(int,int%5B%5D))(int size, int[] perm)`
    
    Creates a permutation matrix of the given size, with a specified permutation of the rows.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[plus](#plus(ci.abidjan.adi.core.JAGMA.Matrix))([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")> B)`
    
    Adds another matrix to the current matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[plus](#plus(T))([T](Matrix.html "type parameter in Matrix") scalarK)`
    
    Adds a scalar to each element of the matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[power](#power(int))(int exponent)`
    
    Raises the matrix to the power of an exponent.
    
    `double`
    
    `[pq_norm](#pq_norm(int,int))(int p, int q)`
    
    Computes the pq-norm of the matrix.
    
    `void`
    
    `[print](#print())()`
    
    Method.
    
    `void`
    
    `[printAsMatlab](#printAsMatlab())()`
    
    Method.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[pseudoinverse](#pseudoinverse())()`
    
    Calculates the pseudoinverse of the matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>[]`
    
    `[qrDecomposition](#qrDecomposition())()`
    
    Performs QR decomposition on the matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>[]`
    
    `[qrDecomposition](#qrDecomposition(java.lang.String))([String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html "class or interface in java.lang") qrMethodName)`
    
    Performs QR decomposition on the matrix by specifying the method name to use.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>[]`
    
    `[qrDecompositionGivens](#qrDecompositionGivens())()`
    
    Performs QR decomposition using the Givens method.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>[]`
    
    `[qrDecompositionGramSchmidt](#qrDecompositionGramSchmidt())()`
    
    Performs QR decomposition using the Gram-Schmidt method.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>[]`
    
    `[qrDecompositionHouseholder](#qrDecompositionHouseholder())()`
    
    Performs QR decomposition using the Householder method.
    
    `int`
    
    `[rank](#rank())()`
    
    Calculates the rank of the matrix.
    
    `void`
    
    `[reshape](#reshape(int,int))(int newRows, int newCols)`
    
    Reshapes the matrix to the specified number of rows and columns.
    
    `void`
    
    `[resize](#resize(int,int))(int newNrows, int newNcols)`
    
    Resizes the matrix to the specified dimensions.
    
    `void`
    
    `[set](#set(int,int,int,int,ci.abidjan.adi.core.JAGMA.Matrix))(int rowStart, int rowEnd, int colStart, int colEnd, [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")> q)`
    
    Sets values in a submatrix of the current matrix, specified by start and end indices for both rows and columns.
    
    `void`
    
    `[set](#set(int,int,int,int,T%5B%5D%5B%5D))(int rowStart, int rowEnd, int colStart, int colEnd, [T](Matrix.html "type parameter in Matrix")[][] values)`
    
    Sets values in a submatrix of the current matrix, specified by start and end indices for both rows and columns.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[set](#set(int,int,ci.abidjan.adi.core.JAGMA.Matrix))(int from, int to, [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")> B)`
    
    Sets a range of elements in the matrix to the values in a given matrix object.
    
    `void`
    
    `[set](#set(int,int,T))(int row, int col, [T](Matrix.html "type parameter in Matrix") value)`
    
    Sets the value of a specific element in the matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[set](#set(int,int,T%5B%5D%5B%5D))(int from, int to, [T](Matrix.html "type parameter in Matrix")[][] arrayB)`
    
    Sets a range of elements in the matrix to the values in a given 2D array.
    
    `void`
    
    `[set](#set(int,T))(int i, [T](Matrix.html "type parameter in Matrix") value)`
    
    Sets the value of a specific element in the matrix's internal 1D array.
    
    `void`
    
    `[setArray](#setArray(T%5B%5D))([T](Matrix.html "type parameter in Matrix")[] array)`
    
    Sets the internal array representing the matrix.
    
    `void`
    
    `[setColumn](#setColumn(int,ci.abidjan.adi.core.JAGMA.Matrix))(int col, [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")> X)`
    
    Sets the specified column of the matrix to the given column.
    
    `void`
    
    `[setNcols](#setNcols(int))(int ncols)`
    
    Sets the number of columns in the matrix.
    
    `void`
    
    `[setNrows](#setNrows(int))(int nrows)`
    
    Sets the number of rows in the matrix.
    
    `void`
    
    `[setRow](#setRow(int,ci.abidjan.adi.core.JAGMA.Matrix))(int row, [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")> X)`
    
    Sets the specified row of the matrix to the given row.
    
    `void`
    
    `[shuffleColumns](#shuffleColumns(int))(int times)`
    
    Shuffles the columns of the matrix.
    
    `void`
    
    `[shuffleColumnValues](#shuffleColumnValues(int,int))(int col, int times)`
    
    Shuffles the values within a specified column of the matrix.
    
    `void`
    
    `[shuffleColumnValues](#shuffleColumnValues(int,int...))(int times, int... col)`
    
    Shuffles the values within specific columns of the matrix.
    
    `void`
    
    `[shuffleRows](#shuffleRows(int))(int times)`
    
    Shuffles the rows of the matrix.
    
    `void`
    
    `[shuffleRowValues](#shuffleRowValues(int,int))(int row, int times)`
    
    Shuffles the values within a specified row of the matrix.
    
    `void`
    
    `[shuffleRowValues](#shuffleRowValues(int,int...))(int times, int... row)`
    
    Shuffles the values within specific rows of the matrix.
    
    `void`
    
    `[shuffleValues](#shuffleValues(int))(int times)`
    
    Shuffles the values in the matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>[]`
    
    `[singularValueDecompositionWithGivensQR](#singularValueDecompositionWithGivensQR())()`
    
    Calculates the Singular Value Decomposition (SVD) of the matrix using Givens QR decomposition.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>[]`
    
    `[singularValueDecompositionWithGramSchmidtQR](#singularValueDecompositionWithGramSchmidtQR())()`
    
    Calculates the Singular Value Decomposition (SVD) of the matrix using Gram-Schmidt QR decomposition.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>[]`
    
    `[singularValueDecompositionWithHouseholderQR](#singularValueDecompositionWithHouseholderQR())()`
    
    Calculates the Singular Value Decomposition (SVD) of the matrix using Householder QR decomposition.
    
    `double[]`
    
    `[singularValues](#singularValues())()`
    
    Method.
    
    `double[]`
    
    `[singularValues](#singularValues(java.lang.String))([String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html "class or interface in java.lang") decompositionMethodName)`
    
    Calculates the singular values of a matrix using a specified decomposition method.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[solve](#solve(ci.abidjan.adi.core.JAGMA.Matrix))([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")> b)`
    
    Solves equations by nonsingular systems and least squares.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[solveEquation](#solveEquation(ci.abidjan.adi.core.JAGMA.Matrix,ci.abidjan.adi.core.JAGMA.Matrix,java.lang.String,boolean))([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")> A, [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")> b, [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html "class or interface in java.lang") decompositionMethodName, boolean substitution)`
    
    Solves a system of linear equations represented by the matrix equation Ax = b.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[strassen](#strassen(ci.abidjan.adi.core.JAGMA.Matrix))([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")> B)`
    
    Performs Strassen's matrix multiplication algorithm.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[strassenMultiply](#strassenMultiply(ci.abidjan.adi.core.JAGMA.Matrix,ci.abidjan.adi.core.JAGMA.Matrix))([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")> A, [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")> B)`
    
    Performs Strassen's matrix multiplication algorithm on two matrices.
    
    `void`
    
    `[swapCols](#swapCols(int,int))(int col1, int col2)`
    
    Swaps or permutes two columns in the matrix.
    
    `void`
    
    `[swapRows](#swapRows(int,int))(int row1, int row2)`
    
    Swaps or permutes two rows in the matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[times](#times(ci.abidjan.adi.core.JAGMA.Matrix))([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")> B)`
    
    Multiplies the current matrix by another matrix.
    
    `[T](Matrix.html "type parameter in Matrix")[]`
    
    `[to1DArray](#to1DArray())()`
    
    Converts the internal 2D array to a 1D array.
    
    `[T](Matrix.html "type parameter in Matrix")[][]`
    
    `[to2DArray](#to2DArray())()`
    
    Converts the internal 1D array to a 2D array.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[toLowerTriangular](#toLowerTriangular())()`
    
    Converts the current matrix into a lower (inferior) triangular matrix.
    
    `[String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html "class or interface in java.lang")`
    
    `[toString](#toString())()`
    
    OTHER USEFUL METHODS
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[toUpperTriangular](#toUpperTriangular())()`
    
    Converts the current matrix into an upper (superior) triangular matrix.
    
    `double`
    
    `[trace](#trace())()`
    
    Computes the trace of the matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[transformToColumnVector](#transformToColumnVector())()`
    
    Transforms the matrix into a column vector.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[transformToRowVector](#transformToRowVector())()`
    
    Transforms the matrix into a row vector.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[transpose](#transpose())()`
    
    Performs the transpose of the matrix in a naive way.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[upperTriangular](#upperTriangular(ci.abidjan.adi.core.JAGMA.Matrix))([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")> B)`
    
    Converts the given matrix into an upper (superior) triangular matrix.
    
    `[Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")>`
    
    `[zeros](#zeros(int,int))(int rows, int cols)`
    
    Creates a matrix filled with zeros.
    
    ### Methods inherited from class java.lang.[Object](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Object.html "class or interface in java.lang")
    
    `[getClass](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Object.html#getClass() "class or interface in java.lang"), [hashCode](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Object.html#hashCode() "class or interface in java.lang"), [notify](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Object.html#notify() "class or interface in java.lang"), [notifyAll](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Object.html#notifyAll() "class or interface in java.lang"), [wait](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Object.html#wait() "class or interface in java.lang"), [wait](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Object.html#wait(long) "class or interface in java.lang"), [wait](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Object.html#wait(long,int) "class or interface in java.lang")`
    

*   Constructor Details
    -------------------
    
    *   ### Matrix
        
        public Matrix()
        
        Default constructor for the Matrix class.
        
        This constructor is provided for scenarios where specific initialization methods are not required. It does not perform any initialization of the matrix elements, leaving the matrix in an uninitialized state.
        
        This constructor is particularly useful when creating an instance of the Matrix class and then immediately initializing it with specific dimensions and values through other methods, such as `#setDimensions(int, int)` and `#setValues(T[])`.
        
        Example usage:
        
         Matrix matrix = new Matrix<>();
         matrix.setDimensions(3, 3);
         matrix.setValues(new Integer\[\] { 1, 2, 3, 4, 5, 6, 7, 8, 9 });
         
        
    *   ### Matrix
        
        public Matrix(int nrows, int ncols, [T](Matrix.html "type parameter in Matrix")\[\] array)
        
        Constructor. Create a matrix from a 1D array with specified dimensions.
        
        This constructor initializes a matrix with the specified number of rows and columns, and populates it with the elements from the provided 1D array. The array should contain the matrix elements in row-major order, meaning that the elements of each row are stored consecutively in the array.
        
        Example usage:
        
         Integer\[\] array = { 1, 2, 3, 4, 5, 6 };
         Matrix matrix = new Matrix<>(2, 3, array);
         
         
        
        Parameters:
        
        `nrows` - The number of rows in the matrix.
        
        `ncols` - The number of columns in the matrix.
        
        `array` - The array representing the matrix in row-major order.
        
    *   ### Matrix
        
        public Matrix([T](Matrix.html "type parameter in Matrix")\[\]\[\] array2D)
        
        Constructor to create a matrix from a 2D array by copying its elements.
        
        This constructor initializes a matrix with the dimensions derived from the given 2D array. It uses the [`System.arraycopy(Object, int, Object, int, int)`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/System.html#arraycopy(java.lang.Object,int,java.lang.Object,int,int) "class or interface in java.lang") method to copy elements from the input array to the internal array representation of the matrix, ensuring that the matrix is populated with the correct elements in row-major order.
        
        Example usage:
        
         Integer\[\]\[\] array2D = { { 1, 2, 3 }, { 4, 5, 6 } };
         Matrix matrix = new Matrix<>(array2D);
         
        
        Parameters:
        
        `array2D` - The base 2D array from which the matrix elements are copied.
        
    *   ### Matrix
        
        public Matrix(int nrows, int ncols)
        
        Constructor to create a null matrix (initialized to 0) with specified dimensions.
        
        This constructor initializes a matrix with the specified number of rows and columns, and populates it with zeros. It uses a generic approach to create an array of type T, where T extends Number, to ensure that the matrix can hold numeric values. The array is initialized to the size of the matrix (rows \* columns) and filled with zeros.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         
        
        Parameters:
        
        `nrows` - The number of rows in the matrix.
        
        `ncols` - The number of columns in the matrix.
        
    *   ### Matrix
        
        public Matrix(int nrows, int ncols, [T](Matrix.html "type parameter in Matrix") value)
        
        Constructor. Create a matrix initialized with a constant value.
        
        This constructor initializes a matrix with the specified number of rows and columns, and fills it with the provided constant value. It uses the [`Arrays.fill(Object[], Object)`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Arrays.html#fill(java.lang.Object%5B%5D,java.lang.Object) "class or interface in java.util") method to populate the internal array with the constant value, ensuring that every element of the matrix is initialized to the specified value.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3, 1.0);
         
        
        Parameters:
        
        `nrows` - The number of rows in the matrix.
        
        `ncols` - The number of columns in the matrix.
        
        `value` - The constant value to initialize the matrix with.
        
    *   ### Matrix
        
        public Matrix([T](Matrix.html "type parameter in Matrix")\[\] array1D, int mrows)
        
        Constructor to create a matrix from a 1D array by specifying the number of rows only.
        
        This constructor initializes a matrix with the specified number of rows and calculates the number of columns based on the length of the provided 1D array and the number of rows. It checks if the total number of elements in the array matches the expected total number of elements for the matrix (rows \* columns). If the sizes do not match, an [`IllegalArgumentException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang") is thrown.
        
        Example usage:
        
         Integer\[\] array1D = { 1, 2, 3, 4, 5, 6 };
         Matrix matrix = new Matrix<>(array1D, 2);
         
        
        Parameters:
        
        `array1D` - The 1D array of real numbers stored in row-major order.
        
        `mrows` - The number of rows in the matrix.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if the array size is not correct.
        
    *   ### Matrix
        
        public Matrix(int mcols, [T](Matrix.html "type parameter in Matrix")\[\] array1D)
        
        Constructor to create a matrix from a 1D array by specifying the number of columns only.
        
        This constructor initializes a matrix with the specified number of columns and calculates the number of rows based on the length of the provided 1D array and the number of columns. It checks if the total number of elements in the array matches the expected total number of elements for the matrix (rows \* columns). If the sizes do not match, an [`IllegalArgumentException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang") is thrown.
        
        Example usage:
        
         Float\[\] array1D = { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0 };
         Matrix matrix = new Matrix<>(3, array1D);
         
        
        Parameters:
        
        `mcols` - The number of columns in the matrix.
        
        `array1D` - The 1D array of real numbers stored in row-major order.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if the array size is not correct.
        
    
*   Method Details
    --------------
    
    *   ### getArray
        
        public [T](Matrix.html "type parameter in Matrix")\[\] getArray()
        
        Retrieves the internal array representing the matrix in 1D format.
        
        This method returns the internal array used to store the matrix elements. The array is in a flat, row-major order, meaning that the elements of each row are stored consecutively in the array.
        
        Example usage:
        
         Matrix matrix = new Matrix(3, 3);
         // Assume matrix is initialized with values
         Double\[\] array = matrix.getArray();
         // This permits to set elements easily as
         array\[0\] = 1.0;
         array\[1\] = 2.0;
         array\[2\] = 3.0;
         array\[3\] = 4.0;
         array\[4\] = 5.0;
         array\[5\] = 6.0;
         array\[6\] = 7.0;
         array\[7\] = 8.0;
         array\[8\] = 9.0;
         
        
        Returns:
        
        The internal array representing the matrix.
        
    *   ### setArray
        
        public void setArray([T](Matrix.html "type parameter in Matrix")\[\] array)
        
        Sets the internal array representing the matrix.
        
        This method updates the internal array used to store the matrix elements. It takes a new array as input and copies its contents using the [`Arrays.copyOf(Object[], int)`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Arrays.html#copyOf(T%5B%5D,int) "class or interface in java.util") method to ensure that the matrix's internal representation is updated correctly.
        
        Example usage:
        
         Matrix matrix = new Matrix(3, 3);
         Short\[\] newArray = new T\[\] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
         matrix.setArray(newArray);
         
        
        Parameters:
        
        `array` - The new array to set as the matrix's internal representation.
        
    *   ### getNrows
        
        public int getNrows()
        
        Retrieves the number of rows in the matrix.
        
        This method returns the number of rows in the matrix as an integer. It is a simple getter method that provides access to the matrix's row count.
        
        Example usage:
        
         Matrix matrix = new Matrix(3, 3);
         // Assume matrix is initialized with values
         int numRows = matrix.getNrows();
         System.out.println("Number of rows: " + numRows);
         
        
        Returns:
        
        The number of rows in the matrix.
        
    *   ### setNrows
        
        public void setNrows(int nrows)
        
        Sets the number of rows in the matrix.
        
        This method updates the number of rows in the matrix. If the new number of rows is different from the current number, it creates a new array with the updated size and copies the existing elements into it, preserving the existing matrix data. This operation may require resizing the internal array to accommodate the new dimensions, ensuring that the matrix's structure remains consistent.
        
        Example usage:
        
         Matrix matrix = new Matrix(3, 3);
         matrix.setNrows(4); // Resizes the matrix to 4 rows
         
        
        Parameters:
        
        `nrows` - The new number of rows for the matrix.
        
    *   ### getNcols
        
        public int getNcols()
        
        Retrieves the number of columns in the matrix.
        
        This method returns the number of columns in the matrix as an integer. It is a simple getter method that provides access to the matrix's column count.
        
        Example usage:
        
         Matrix matrix = new Matrix(3, 3);
         int numCols = matrix.getNcols();
         System.out.println("Number of columns: " + numCols);
         
        
        Returns:
        
        The number of columns in the matrix.
        
    *   ### setNcols
        
        public void setNcols(int ncols)
        
        Sets the number of columns in the matrix.
        
        This method updates the number of columns in the matrix. If the new number of columns is different from the current number, it creates a new array with the updated size and copies the existing elements into it, preserving the existing matrix data. This operation may require resizing the internal array to accommodate the new dimensions, ensuring that the matrix's structure remains consistent.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         matrix.setNcols(4); // Resizes the matrix to 4 columns
         
        
        Parameters:
        
        `ncols` - The new number of columns for the matrix.
        
    *   ### get
        
        public [T](Matrix.html "type parameter in Matrix") get(int row, int col)
        
        Retrieves the value of a specific element in the matrix.
        
        This method returns the value of the element at the specified row and column indices. The indices are zero-based, meaning that the first element is at row 0, column 0.
        
        Example usage:
        
         Matrix matrix = new Matrix(2, 2);
         // Fill the matrix with values
         matrix.getArray()\[0\] = 1;
         matrix.getArray()\[1\] = 2;
         matrix.getArray()\[2\] = 3;
         matrix.getArray()\[3\] = 4;
         // Assume matrix is initialized with values
         Long value = matrix.get(0, 1); // Retrieves the value at row 0, column 1
         
        
        Parameters:
        
        `row` - The row index of the element to retrieve.
        
        `col` - The column index of the element to retrieve.
        
        Returns:
        
        The value of the element at the specified row and column indices.
        
    *   ### set
        
        public void set(int row, int col, [T](Matrix.html "type parameter in Matrix") value)
        
        Sets the value of a specific element in the matrix.
        
        This method updates the value of the element at the specified row and column indices. The indices are zero-based, meaning that the first element is at row 0, column 0.
        
        Example usage:
        
         Matrix matrix = new Matrix(3, 3);
         matrix.set(1, 2, 5.0); // Sets the value at row 1, column 2 to 5.0
         
        
        Parameters:
        
        `row` - The row index of the element to update.
        
        `col` - The column index of the element to update.
        
        `value` - The new value to set for the specified element.
        
    *   ### get
        
        public [T](Matrix.html "type parameter in Matrix") get(int i)
        
        Retrieves the value of a specific element in the matrix's internal 1D array.
        
        This method returns the value of the element at the specified index in the internal 1D array representing the matrix. The index is zero-based, and it directly corresponds to the row-major order of the matrix's elements.
        
        Example usage:
        
         Matrix matrix = new Matrix(3, 3);
         // Assume matrix is initialized with values
         Integer value = matrix.get(2); // Retrieves the value at index 2 in the internal array
         
        
        Parameters:
        
        `i` - The index in the internal 1D array of the element to retrieve.
        
        Returns:
        
        The value of the element at the specified index.
        
    *   ### set
        
        public void set(int i, [T](Matrix.html "type parameter in Matrix") value)
        
        Sets the value of a specific element in the matrix's internal 1D array.
        
        This method updates the value of the element at the specified index in the internal 1D array representing the matrix. The index is zero-based, and it directly corresponds to the row-major order of the matrix's elements.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         matrix.set(2, 10.0); // Sets the value at index 2 in the internal array to 10.0
         
        
        Parameters:
        
        `i` - The index in the internal 1D array of the element to update.
        
        `value` - The new value to set for the specified element.
        
    *   ### set
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> set(int from, int to, [T](Matrix.html "type parameter in Matrix")\[\]\[\] arrayB)
        
        Sets a range of elements in the matrix to the values in a given 2D array.
        
        This method updates a range of elements in the matrix, starting from the specified starting index and ending before the specified ending index, with the values from the provided 2D array. The method checks if the indices and array size are valid to ensure that the matrix's structure remains consistent. If the indices are not valid or the array size does not match the expected range, an [`IllegalArgumentException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang") is thrown.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         Integer\[\]\[\] newValues = { { 7, 8, 9 }, { 10, 11, 12 } };
         matrix.set(0, 6, newValues); // Sets the values from index 0 to index 5
         
        
        Parameters:
        
        `from` - The starting index (inclusive) for setting new values.
        
        `to` - The ending index (exclusive) for setting new values.
        
        `arrayB` - The 2D array containing the new values.
        
        Returns:
        
        The current matrix after setting the new values.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if the indices are not valid or the array size is not correct.
        
    *   ### set
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> set(int from, int to, [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> B)
        
        Sets a range of elements in the matrix to the values in a given matrix object.
        
        This method updates a range of elements in the matrix, starting from the specified starting index and ending before the specified ending index, with the values from another matrix object. The method checks if the indices and the size of the matrix object are valid to ensure that the matrix's structure remains consistent. If the indices are not valid or the matrix object's size does not match the expected range, an [`IllegalArgumentException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang") is thrown.
        
        Example usage:
        
         Matrix matrixA = new Matrix<>(3, 3);
         // Assume matrixA is initialized with values
         Matrix matrixB = new Matrix<>(2, 2);
         // Assume matrixB is initialized with different values
         matrixA.set(0, 4, matrixB); // Sets the values from matrixB into matrixA starting at index 0
         
        
        Parameters:
        
        `from` - The starting index (inclusive) for setting new values.
        
        `to` - The ending index (exclusive) for setting new values.
        
        `B` - The matrix object containing the new values.
        
        Returns:
        
        The current matrix after setting the new values.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if the indices are not valid or the array size is not correct.
        
    *   ### set
        
        public void set(int rowStart, int rowEnd, int colStart, int colEnd, [T](Matrix.html "type parameter in Matrix")\[\]\[\] values)
        
        Sets values in a submatrix of the current matrix, specified by start and end indices for both rows and columns.
        
        This method updates a submatrix of the current matrix with the values provided in a 2D array. The submatrix is defined by the start and end indices for both rows and columns. The method checks if the indices are valid and if the dimensions of the values array match the size of the submatrix. If the indices are invalid or the dimensions do not match, an [`IllegalArgumentException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang") is thrown.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with values
         Double\[\]\[\] newValues = { { 7.0, 8.0 }, { 9.0, 10.0 } };
         matrix.set(1, 2, 3, 4, newValues); // Sets the values from newValues into the submatrix of matrix
         
        
        Parameters:
        
        `rowStart` - The start index for the row (inclusive).
        
        `rowEnd` - The end index for the row (exclusive).
        
        `colStart` - The start index for the column (inclusive).
        
        `colEnd` - The end index for the column (exclusive).
        
        `values` - A 2D array of values to set in the submatrix. The array must have dimensions equal to the size of the submatrix.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - If the indices are invalid or the dimensions of values do not match the submatrix size.
        
    *   ### set
        
        public void set(int rowStart, int rowEnd, int colStart, int colEnd, [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> q)
        
        Sets values in a submatrix of the current matrix, specified by start and end indices for both rows and columns.
        
        This method updates a submatrix of the current matrix with the values provided in another matrix object. The submatrix is defined by the start and end indices for both rows and columns. The method checks if the indices are valid and if the dimensions of the values matrix match the size of the submatrix. If the indices are invalid or the dimensions do not match, an [`IllegalArgumentException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang") is thrown.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with values
         Matrix subMatrix = new Matrix<>(2, 2);
         // Assume subMatrix is initialized with different values
         matrix.set(1, 2, 3, 4, subMatrix); // Sets the values from subMatrix into the submatrix of matrix
         
        
        Parameters:
        
        `rowStart` - The start index for the row (inclusive).
        
        `rowEnd` - The end index for the row (exclusive).
        
        `colStart` - The start index for the column (inclusive).
        
        `colEnd` - The end index for the column (exclusive).
        
        `q` - A matrix object of values to set in the submatrix. The array must have dimensions equal to the size of the submatrix.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - If the indices are invalid or the dimensions of values do not match the submatrix size.
        
    *   ### generateRandomMatrix
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> generateRandomMatrix(int nrows, int ncols)
        
        Generates a matrix filled with random values.
        
        This method creates a new matrix of the specified dimensions and fills it with random values. The type of the values is determined by the generic type parameter T, which should extend Number to accommodate various numeric types.
        
        Example usage:
        
         Matrix matrix = Matrix.generateRandomMatrix(3, 3);
         // matrix is now filled with random Double values
         
        
        Parameters:
        
        `nrows` - Number of rows for the new matrix.
        
        `ncols` - Number of columns for the new matrix.
        
        Returns:
        
        A new matrix filled with random values.
        
    *   ### generateBinaryMatrix
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> generateBinaryMatrix(int nrows, int ncols)
        
        Generates a binary matrix filled with 0s and 1s.
        
        This method creates a new matrix of the specified dimensions and fills it with binary values (0s and 1s) generated randomly. The type of the values is determined by the generic type parameter T, which is expected to be an Integer or a type that can be cast from an Integer. This method is particularly useful for generating matrices for binary operations or matrices with binary values, such as adjacency matrices or matrices used in boolean logic operations.
        
        Example usage:
        
         Matrix binaryMatrix = generateBinaryMatrix(3, 3);
         // binaryMatrix is now filled with random 0s and 1s
         
        
        Parameters:
        
        `nrows` - Number of rows for the new binary matrix.
        
        `ncols` - Number of columns for the new binary matrix.
        
        Returns:
        
        A new matrix filled with binary values (0s and 1s).
        
    *   ### to2DArray
        
        public [T](Matrix.html "type parameter in Matrix")\[\]\[\] to2DArray()
        
        Converts the internal 1D array to a 2D array.
        
        This method transforms the internal 1D array representation of the matrix into a 2D array, where each row of the 2D array corresponds to a row in the matrix. This conversion is useful for operations that require a 2D view of the matrix data, such as iterating over rows and columns directly.
        
        Example usage:
        
         Matrix matrix = new Matrix(3, 3);
         // Assume matrix is initialized with values
         T\[\]\[\] matrix2D = matrix.to2DArray();
         // matrix2D now represents the matrix in a 2D format
         
        
        Returns:
        
        The internal array as a 2D array.
        
    *   ### to1DArray
        
        public [T](Matrix.html "type parameter in Matrix")\[\] to1DArray()
        
        Converts the internal 2D array to a 1D array.
        
        This method transforms the internal 2D array representation of the matrix into a 1D array, effectively flattening the matrix structure. This conversion is useful for operations that require a linear view of the matrix data, such as certain mathematical operations or when interfacing with APIs that expect 1D arrays.
        
        Example usage:
        
         Matrix matrix = new Matrix(3, 3);
         // Assume matrix is initialized with values
         T\[\] matrix1D = matrix.to1DArray();
         // matrix1D now represents the matrix in a 1D format
         
        
        Returns:
        
        The internal array as a 1D array.
        
    *   ### clone
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> clone()
        
        Clones (or makes a copy of) the current matrix.
        
        This method creates a new matrix that is a deep copy of the current matrix. A deep copy means that all elements of the matrix are copied, and the new matrix is independent of the original matrix. This is useful for scenarios where you need to modify the matrix without affecting the original data.
        
        Example usage:
        
         Matrix matrix = new Matrix(3, 3);
         // Assume matrix is initialized with values
         Matrix matrixCopy = matrix.clone();
         // matrixCopy is now a separate copy of the original matrix
         
        
        Returns:
        
        A new matrix that is a clone of the current matrix.
        
    *   ### extractRowsRange
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> extractRowsRange(int from, int to)
        
        Extracts a range of rows from the matrix.
        
        This method creates a new matrix that contains a specified range of rows from the original matrix. The range is defined by the starting and ending indices, where the starting index is inclusive and the ending index is exclusive. If the indices are not valid, an [`IllegalArgumentException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang") is thrown.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix extractedRows = matrix.extractRowsRange(1, 3);
         // extractedRows now contains the rows from index 1 to index 2 of the
         // original matrix
         
        
        Parameters:
        
        `from` - The starting index (inclusive) of the row range to extract.
        
        `to` - The ending index (exclusive) of the row range to extract.
        
        Returns:
        
        A new matrix that contains the specified range of rows.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if the indices are not valid.
        
    *   ### extractColumnsRange
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> extractColumnsRange(int from, int to)
        
        Extracts a range of columns from the matrix.
        
        This method creates a new matrix that contains a specified range of columns from the original matrix. The range is defined by the starting and ending indices, where the starting index is inclusive and the ending index is exclusive. If the indices are not valid, an [`IllegalArgumentException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang") is thrown.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix extractedColumns = matrix.extractColumnsRange(1, 3);
         // extractedColumns now contains the columns from index 1 to index 2 of
         // the original matrix
         
        
        Parameters:
        
        `from` - The starting index (inclusive) of the column range to extract.
        
        `to` - The ending index (exclusive) of the column range to extract.
        
        Returns:
        
        A new matrix that contains the specified range of columns.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if the indices are not valid.
        
    *   ### extractRow
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> extractRow(int i)
        
        Extracts a specific row from the matrix.
        
        This method creates a new matrix that contains a single row from the original matrix. The row is specified by its index. If the row index is not valid, an [`IllegalArgumentException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang") is thrown.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix extractedRow = matrix.extractRow(1);
         // extractedRow now contains the row at index 1 of the original matrix
         
        
        Parameters:
        
        `i` - The index of the row to extract.
        
        Returns:
        
        A new matrix that is a single row of the original matrix.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if the row index is not valid.
        
    *   ### getRow
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> getRow(int row)
        
        Returns the specified row of the matrix as a new matrix.
        
        This method extracts a single row from the original matrix and returns it as a new matrix. The row is specified by its index. If the row index is not valid, an [`IllegalArgumentException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang") is thrown.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix rowMatrix = matrix.getRow(1);
         // rowMatrix now contains the row at index 1 of the original matrix
         
        
        Parameters:
        
        `row` - The index of the row to return.
        
        Returns:
        
        The specified row as a new matrix.
        
        See Also:
        
        *   [`extractRow(int)`](#extractRow(int))
        
    *   ### setRow
        
        public void setRow(int row, [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> X)
        
        Sets the specified row of the matrix to the given row.
        
        This method replaces a row in the matrix with a new row provided as a matrix. The row to be replaced is specified by its index, and the new row must be a matrix with the same number of columns as the original matrix. If the new row matrix does not have the correct dimensions, an [`IllegalArgumentException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang") is thrown.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix newRowMatrix = new Matrix<>(1, 3);
         // Initialize newRowMatrix with new values
         matrix.setRow(1, newRowMatrix);
         // The row at index 1 of matrix is now replaced with newRowMatrix
         
        
        Parameters:
        
        `row` - The index of the row to set.
        
        `X` - The new row matrix.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if the new row matrix dimensions are not valid.
        
    *   ### extractCol
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> extractCol(int i)
        
        Extracts a specific column from the matrix.
        
        This method creates a new matrix that contains a single column from the original matrix. The column is specified by its index. If the column index is not valid, an [`IllegalArgumentException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang") is thrown.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix columnMatrix = matrix.extractCol(1);
         // columnMatrix now contains the column at index 1 of the original matrix
         
        
        Parameters:
        
        `i` - The index of the column to extract.
        
        Returns:
        
        A new matrix that is a single column of the original matrix.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if the column index is not valid.
        
        See Also:
        
        *   [`getColumn(int)`](#getColumn(int))
        
    *   ### getColumn
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> getColumn(int col)
        
        Returns the specified column of the matrix as a new matrix.
        
        This method extracts a single column from the original matrix and returns it as a new matrix. The column is specified by its index. If the column index is not valid, an [`IllegalArgumentException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang") is thrown.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix columnMatrix = matrix.getColumn(1);
         // columnMatrix now contains the column at index 1 of the original matrix
         
        
        Parameters:
        
        `col` - The index of the column to return.
        
        Returns:
        
        The specified column as a new matrix.
        
        See Also:
        
        *   [`extractCol(int)`](#extractCol(int))
        
    *   ### setColumn
        
        public void setColumn(int col, [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> X)
        
        Sets the specified column of the matrix to the given column.
        
        This method replaces a column in the matrix with a new column provided as a matrix. The column to be replaced is specified by its index, and the new column must be a matrix with the same number of rows as the original matrix. If the new column matrix does not have the correct dimensions, an [`IllegalArgumentException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang") is thrown.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix newColumnMatrix = new Matrix<>(3, 1);
         // Initialize newColumnMatrix with new values
         matrix.setColumn(1, newColumnMatrix);
         // The column at index 1 of matrix is now replaced with newColumnMatrix
         
        
        Parameters:
        
        `col` - The index of the column to set.
        
        `X` - The new column matrix.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if the new column matrix dimensions are not valid.
        
    *   ### extract
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> extract(int rowstart, int rowend, int colstart, int colend)
        
        Extracts a subMatrix from the matrix.
        
        This method creates a new matrix that contains a submatrix of the original matrix. The submatrix is defined by the starting and ending indices for both rows and columns. If the indices are not valid, an [`IllegalArgumentException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang") is thrown.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix subMatrix = matrix.extract(1, 3, 1, 3);
         // subMatrix now contains the submatrix from row 1 to row 2 and column 1
         // to column 2 of the original matrix
         
        
        Parameters:
        
        `rowstart` - The starting row index (inclusive) of the subMatrix to extract.
        
        `rowend` - The ending row index (exclusive) of the subMatrix to extract.
        
        `colstart` - The starting column index (inclusive) of the subMatrix to extract.
        
        `colend` - The ending column index (exclusive) of the subMatrix to extract.
        
        Returns:
        
        A new matrix that is a subMatrix of the original matrix.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if the indices are not valid.
        
    *   ### transformToRowVector
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> transformToRowVector()
        
        Transforms the matrix into a row vector.
        
        This method converts the matrix into a row vector by creating a new matrix with a single row and the same number of columns as the original matrix. This operation is useful for flattening the matrix into a single row, which can be useful for certain matrix operations or when interfacing with APIs that expect a single row vector.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix rowVector = matrix.transformToRowVector();
         // rowVector now represents the matrix as a single row vector
         
        
        Returns:
        
        The matrix transformed into a row vector.
        
    *   ### transformToColumnVector
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> transformToColumnVector()
        
        Transforms the matrix into a column vector.
        
        This method converts the matrix into a column vector by creating a new matrix with a single column and the same number of rows as the original matrix. This operation is useful for flattening the matrix into a single column, which can be useful for certain matrix operations or when interfacing with APIs that expect a single column vector.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix columnVector = matrix.transformToColumnVector();
         // columnVector now represents the matrix as a single column vector
         
        
        Returns:
        
        The matrix transformed into a column vector.
        
    *   ### concatenateHorizontally
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> concatenateHorizontally([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\>... matrices)
        
        Concatenates multiple matrices horizontally.
        
        This method concatenates the current matrix with one or more additional matrices horizontally. The operation requires that all matrices have the same number of rows. The resulting matrix will have the same number of rows as the original matrix but its number of columns will be the sum of the columns of all matrices.
        
        Example usage:
        
         Matrix matrix1 = new Matrix<>(3, 3);
         // Assume matrix1 is initialized with Double values
         Matrix matrix2 = new Matrix<>(3, 2);
         // Assume matrix2 is initialized with Double values
         Matrix concatenated = matrix1.concatenateHorizontally(matrix2);
         // concatenated now contains the concatenation of matrix1 and matrix2
         // horizontally
         
        
        Parameters:
        
        `matrices` - Varargs parameter representing the matrices to concatenate.
        
        Returns:
        
        The concatenated matrix.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if the matrices do not have the same number of rows.
        
    *   ### concatenateVertically
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> concatenateVertically([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\>... matrices)
        
        Concatenates multiple matrices vertically.
        
        This method concatenates the current matrix with one or more additional matrices vertically. The operation requires that all matrices have the same number of columns. The resulting matrix will have the same number of columns as the original matrix but its number of rows will be the sum of the rows of all matrices.
        
        Example usage:
        
         Matrix matrix1 = new Matrix<>(3, 3);
         // Assume matrix1 is initialized with Double values
         Matrix matrix2 = new Matrix<>(2, 3);
         // Assume matrix2 is initialized with Double values
         Matrix concatenated = matrix1.concatenateVertically(matrix2);
         // concatenated now contains the concatenation of matrix1 and matrix2
         // vertically
         
        
        Parameters:
        
        `matrices` - Varargs parameter representing the matrices to concatenate.
        
        Returns:
        
        The concatenated matrix.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if the matrices do not have the same number of columns.
        
    *   ### insertRowsBefore
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> insertRowsBefore(int indexBefore, [T](Matrix.html "type parameter in Matrix")\[\]... y)
        
        Inserts rows before a specified index in the matrix.
        
        This method inserts one or more rows before a specified index in the matrix. The rows to be inserted are provided as a varargs parameter of arrays, where each array represents a row. If the index is not valid, an [`IllegalArgumentException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang") is thrown.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Double\[\]\[\] rowsToInsert = { { 1.0, 2.0, 3.0 }, { 4.0, 5.0, 6.0 } };
         Matrix updatedMatrix = matrix.insertRowsBefore(1, rowsToInsert);
         // updatedMatrix now contains the original matrix with two new rows
         // inserted before the second row
         
        
        Parameters:
        
        `indexBefore` - The index before which to insert the rows.
        
        `y` - The rows to insert, provided as varargs of arrays.
        
        Returns:
        
        The current matrix after inserting the rows.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if the index is not valid.
        
    *   ### insertColumnsBefore
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> insertColumnsBefore(int indexBefore, [T](Matrix.html "type parameter in Matrix")\[\]... y)
        
        Inserts columns before a specified index in the matrix.
        
        This method inserts one or more columns before a specified index in the matrix. The columns to be inserted are provided as a varargs parameter of arrays, where each array represents a column. If the index is not valid, an [`IllegalArgumentException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang") is thrown.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Double\[\]\[\] columnsToInsert = { { 1.0 }, { 2.0 }, { 3.0 } };
         Matrix updatedMatrix = matrix.insertColumnsBefore(1, columnsToInsert);
         // updatedMatrix now contains the original matrix with three new columns
         // inserted before the second column
         
        
        Parameters:
        
        `indexBefore` - The index before which to insert the columns.
        
        `y` - The columns to insert, provided as varargs of arrays.
        
        Returns:
        
        The current matrix after inserting the columns.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if the index is not valid.
        
    *   ### insertValuesBefore
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> insertValuesBefore(int indexBefore, [T](Matrix.html "type parameter in Matrix")... y)
        
        Inserts values before a specified index in the matrix.
        
        This method inserts one or more values before a specified index in the matrix. The values to be inserted are provided as varargs, where each value represents an element of the matrix. If the index is not valid, an [`IllegalArgumentException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang") is thrown.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Double\[\] valuesToInsert = { 1.0, 2.0, 3.0 };
         Matrix updatedMatrix = matrix.insertValuesBefore(1, valuesToInsert);
         // updatedMatrix now contains the original matrix with three new values
         // inserted before the second element
         
        
        Parameters:
        
        `indexBefore` - The index before which to insert the values.
        
        `y` - The values to insert, provided as varargs.
        
        Returns:
        
        The current matrix after inserting the values.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if the index is not valid.
        
    *   ### insertRowsBefore
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> insertRowsBefore(int indexBefore, [T](Matrix.html "type parameter in Matrix")\[\]\[\]... y)
        
        Inserts rows before a specified index in the matrix.
        
        This method inserts one or more rows before a specified index in the matrix. The rows to be inserted are provided as varargs of arrays, where each array represents a row. If the index is not valid, an [`IllegalArgumentException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang") is thrown.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Double\[\]\[\]\[\] rowsToInsert = { { new Double\[\] { 1.0, 2.0, 3.0 } }, { new Double\[\] { 4.0, 5.0, 6.0 } } };
         Matrix updatedMatrix = matrix.insertRowsBefore(1, rowsToInsert);
         // updatedMatrix now contains the original matrix with two new rows
         // inserted before the second row
         
        
        Parameters:
        
        `indexBefore` - The index before which to insert the rows.
        
        `y` - The rows to insert, provided as varargs of arrays.
        
        Returns:
        
        The current matrix after inserting the rows.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if the index is not valid.
        
    *   ### insertAfter
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> insertAfter(int indexAfter, [T](Matrix.html "type parameter in Matrix")\[\]\[\]... y)
        
        Inserts rows after a specified index in the matrix.
        
        This method inserts one or more rows after a specified index in the matrix. The rows to be inserted are provided as varargs of arrays, where each array represents a row. If the index is not valid, an [`IllegalArgumentException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang") is thrown.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Double\[\]\[\]\[\] rowsToInsert = { { new Double\[\] { 1.0, 2.0, 3.0 } }, { new Double\[\] { 4.0, 5.0, 6.0 } } };
         Matrix updatedMatrix = matrix.insertAfter(1, rowsToInsert);
         // updatedMatrix now contains the original matrix with two new rows
         // inserted after the second row
         
        
        Parameters:
        
        `indexAfter` - The index after which to insert the rows.
        
        `y` - The rows to insert, provided as varargs of arrays.
        
        Returns:
        
        The current matrix after inserting the rows.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if the index is not valid.
        
    *   ### insertColumnsBefore
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> insertColumnsBefore(int indexBefore, [T](Matrix.html "type parameter in Matrix")\[\]\[\]... y)
        
        Inserts columns before a specified index in the matrix.
        
        This method inserts one or more columns before a specified index in the matrix. The columns to be inserted are provided as varargs of arrays, where each array represents a column. If the index is not valid, an [`IllegalArgumentException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang") is thrown.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Double\[\]\[\]\[\] columnsToInsert = { { new Double\[\] { 1.0, 2.0, 3.0 } }, { new Double\[\] { 4.0, 5.0, 6.0 } } };
         Matrix updatedMatrix = matrix.insertColumnsBefore(1, columnsToInsert);
         // updatedMatrix now contains the original matrix with two new columns
         // inserted before the second column
         
        
        Parameters:
        
        `indexBefore` - The index before which to insert the columns.
        
        `y` - The columns to insert, provided as varargs of arrays.
        
        Returns:
        
        The current matrix after inserting the columns.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if the index is not valid.
        
    *   ### insertColumnsAfter
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> insertColumnsAfter(int indexAfter, [T](Matrix.html "type parameter in Matrix")\[\]\[\]... y)
        
        Inserts columns after a specified index in the matrix.
        
        This method inserts one or more columns after a specified index in the matrix. The columns to be inserted are provided as varargs of arrays, where each array represents a column. If the index is not valid, an [`IllegalArgumentException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang") is thrown.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Double\[\]\[\]\[\] columnsToInsert = { { new Double\[\] { 1.0, 2.0, 3.0 } }, { new Double\[\] { 4.0, 5.0, 6.0 } } };
         Matrix updatedMatrix = matrix.insertColumnsAfter(1, columnsToInsert);
         // updatedMatrix now contains the original matrix with two new columns
         // inserted after the second column
         
        
        Parameters:
        
        `indexAfter` - The index after which to insert the columns.
        
        `y` - The columns to insert, provided as varargs of arrays.
        
        Returns:
        
        The current matrix after inserting the columns.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if the index is not valid.
        
    *   ### deleteRowsRange
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> deleteRowsRange(int rowstart, int rowend)
        
        Deletes a range of rows from the matrix.
        
        This method removes a range of rows from the matrix, starting from the specified start index up to, but not including, the specified end index. If the indices are not valid, an [`IllegalArgumentException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang") is thrown.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix updatedMatrix = matrix.deleteRowsRange(1, 3);
         // updatedMatrix now contains the original matrix with the second row
         // removed
         
        
        Parameters:
        
        `rowstart` - The starting row index (inclusive).
        
        `rowend` - The ending row index (exclusive).
        
        Returns:
        
        The current matrix after deleting the rows.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if the indices are not valid.
        
    *   ### deleteColumnsRange
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> deleteColumnsRange(int colstart, int colend)
        
        Deletes a range of columns from the matrix.
        
        This method removes a range of columns from the matrix, starting from the specified start index up to, but not including, the specified end index. If the indices are not valid, an [`IllegalArgumentException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang") is thrown.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix updatedMatrix = matrix.deleteColumnsRange(1, 3);
         // updatedMatrix now contains the original matrix with the second and
         // third columns removed
         
        
        Parameters:
        
        `colstart` - The starting column index (inclusive).
        
        `colend` - The ending column index (exclusive).
        
        Returns:
        
        The current matrix after deleting the columns.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if the indices are not valid.
        
    *   ### deleteRows
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> deleteRows(int... indices)
        
        Deletes specific rows from the matrix.
        
        This method removes specific rows from the matrix based on the provided indices. The indices are specified as varargs, where each value represents the index of a row to be removed. If any index is not valid, an [`IllegalArgumentException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang") is thrown.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix updatedMatrix = matrix.deleteRows(1, 2);
         // updatedMatrix now contains the original matrix with the second and
         // third rows removed
         
        
        Parameters:
        
        `indices` - The list of the indices of the rows to delete.
        
        Returns:
        
        The current matrix after deleting the rows.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if any index is not valid.
        
    *   ### deleteColumns
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> deleteColumns(int... indices)
        
        Deletes specific columns from the matrix.
        
        This method removes specific columns from the matrix based on the provided indices. The indices are specified as varargs, where each value represents the index of a column to be removed. If any index is not valid, an [`IllegalArgumentException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang") is thrown.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix updatedMatrix = matrix.deleteColumns(1, 2);
         // updatedMatrix now contains the original matrix with the second and
         // third columns removed
         
        
        Parameters:
        
        `indices` - The list of the indices of the columns to delete.
        
        Returns:
        
        The current matrix after deleting the columns.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if any index is not valid.
        
    *   ### resize
        
        public void resize(int newNrows, int newNcols)
        
        Resizes the matrix to the specified dimensions.
        
        This method adjusts the size of the matrix to the specified number of rows and columns. It creates a new array with the new dimensions and copies the elements from the old array to the new array, preserving as much of the original data as possible. If the new dimensions are larger than the original, the new elements are initialized with the default value for the type of the matrix's elements.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         matrix.resize(2, 2);
         // matrix now has dimensions 2x2
         
        
        Parameters:
        
        `newNrows` - The new number of rows.
        
        `newNcols` - The new number of columns.
        
    *   ### swapRows
        
        public void swapRows(int row1, int row2)
        
        Swaps or permutes two rows in the matrix.
        
        This method swaps the positions of two specified rows in the matrix. It is useful for reordering rows without needing to manually copy and paste data. If the indices are not valid, an [`IllegalArgumentException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang") is thrown.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         matrix.swapRows(0, 2);
         // The first and third rows of the matrix are now swapped
         
        
        Parameters:
        
        `row1` - The index of the first row.
        
        `row2` - The index of the second row.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if the row indices are not valid.
        
    *   ### swapCols
        
        public void swapCols(int col1, int col2)
        
        Swaps or permutes two columns in the matrix.
        
        This method swaps the positions of two specified columns in the matrix. It is useful for reordering columns without needing to manually copy and paste data. If the indices are not valid, an [`IllegalArgumentException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang") is thrown.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         matrix.swapCols(0, 2);
         // The first and third columns of the matrix are now swapped
         
        
        Parameters:
        
        `col1` - The index of the first column.
        
        `col2` - The index of the second column.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if the column indices are not valid.
        
    *   ### shuffleRows
        
        public void shuffleRows(int times)
        
        Shuffles the rows of the matrix.
        
        This method shuffles the rows of the matrix a specified number of times. Each shuffle involves swapping each row with a randomly selected row. This method is useful for randomizing the order of rows in the matrix.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         matrix.shuffleRows(5);
         // The rows of the matrix have been shuffled 5 times
         
        
        Parameters:
        
        `times` - The number of times to shuffle.
        
    *   ### shuffleColumns
        
        public void shuffleColumns(int times)
        
        Shuffles the columns of the matrix.
        
        This method shuffles the columns of the matrix a specified number of times. Each shuffle involves swapping each column with a randomly selected column. This method is useful for randomizing the order of columns in the matrix.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         matrix.shuffleColumns(5);
         // The columns of the matrix have been shuffled 5 times
         
        
        Parameters:
        
        `times` - The number of times to shuffle.
        
    *   ### shuffleRowValues
        
        public void shuffleRowValues(int row, int times)
        
        Shuffles the values within a specified row of the matrix.
        
        This method shuffles the values within a specified row of the matrix a specified number of times. Each shuffle involves swapping each value in the row with a randomly selected value within the same row. This method is useful for randomizing the order of values within a specific row.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         matrix.shuffleRowValues(0, 5);
         // The values within the first row of the matrix have been shuffled 5
         // times
         
        
        Parameters:
        
        `row` - The index of the row.
        
        `times` - The number of times to shuffle.
        
    *   ### shuffleColumnValues
        
        public void shuffleColumnValues(int col, int times)
        
        Shuffles the values within a specified column of the matrix.
        
        This method shuffles the values within a specified column of the matrix a specified number of times. Each shuffle involves swapping each value in the column with a randomly selected value within the same column. This method is useful for randomizing the order of values within a specific column.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         matrix.shuffleColumnValues(0, 5);
         // The values within the first column of the matrix have been shuffled 5
         // times
         
        
        Parameters:
        
        `col` - The index of the column.
        
        `times` - The number of times to shuffle.
        
    *   ### shuffleValues
        
        public void shuffleValues(int times)
        
        Shuffles the values in the matrix.
        
        This method shuffles the values in the entire matrix a specified number of times. Each shuffle involves swapping each value in the matrix with a randomly selected value from anywhere in the matrix. This method is useful for randomizing the order of all values in the matrix.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         matrix.shuffleValues(5);
         // The values in the matrix have been shuffled 5 times
         
        
        Parameters:
        
        `times` - The number of times to shuffle.
        
    *   ### shuffleRowValues
        
        public void shuffleRowValues(int times, int... row)
        
        Shuffles the values within specific rows of the matrix.
        
        This method shuffles the values within specified rows of the matrix a specified number of times. Each shuffle involves swapping each value in the specified rows with a randomly selected value within the same row. This method is useful for randomizing the order of values within specific rows.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         matrix.shuffleRowValues(5, 0, 2);
         // The values within the first and third rows of the matrix have been
         // shuffled 5 times
         
        
        Parameters:
        
        `times` - The number of times to shuffle.
        
        `row` - The indices of the rows.
        
    *   ### shuffleColumnValues
        
        public void shuffleColumnValues(int times, int... col)
        
        Shuffles the values within specific columns of the matrix.
        
        This method shuffles the values within specified columns of the matrix a specified number of times. Each shuffle involves swapping each value in the specified columns with a randomly selected value within the same column. This method is useful for randomizing the order of values within specific columns.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         matrix.shuffleColumnValues(5, 0, 2);
         // The values within the first and third columns of the matrix have been
         // shuffled 5 times
         
        
        Parameters:
        
        `times` - The number of times to shuffle.
        
        `col` - The indices of the columns to shuffle.
        
    *   ### plus
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> plus([T](Matrix.html "type parameter in Matrix") scalarK)
        
        Adds a scalar to each element of the matrix.
        
        This method adds a specified scalar value to each element of the matrix, returning a new matrix with the result. The operation is element-wise, meaning that the scalar is added to each element individually.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix result = matrix.plus(2.0);
         // result now contains the original matrix with each element increased by
         // 2.0
         
        
        Parameters:
        
        `scalarK` - The scalar to add.
        
        Returns:
        
        The current matrix after adding the scalar.
        
    *   ### minus
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> minus([T](Matrix.html "type parameter in Matrix") scalarK)
        
        Subtracts a scalar from each element of the matrix.
        
        This method subtracts a specified scalar value from each element of the matrix, returning a new matrix with the result. The operation is element-wise, meaning that the scalar is subtracted from each element individually.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix result = matrix.minus(2.0);
         // result now contains the original matrix with each element decreased by
         // 2.0
         
        
        Parameters:
        
        `scalarK` - The scalar to subtract.
        
        Returns:
        
        The current matrix after subtracting the scalar.
        
    *   ### plus
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> plus([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> B)
        
        Adds another matrix to the current matrix.
        
        This method adds another matrix to the current matrix, element-wise. The result is a new matrix where each element is the sum of the corresponding elements in the two matrices. If the dimensions of the two matrices do not match, an [`IllegalArgumentException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang") is thrown.
        
        Example usage:
        
         Matrix matrix1 = new Matrix<>(3, 3);
         Matrix matrix2 = new Matrix<>(3, 3);
         // Assume matrix1 and matrix2 are initialized with Double values
         Matrix result = matrix1.plus(matrix2);
         // result now contains the sum of matrix1 and matrix2, element-wise
         
        
        Parameters:
        
        `B` - The matrix to be added to the current matrix.
        
        Returns:
        
        A new matrix object that is the result of adding the input matrix to the current matrix.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - If the dimensions of the two matrices do not match.
        
    *   ### minus
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> minus([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> B)
        
        Subtracts another matrix from the current matrix.
        
        This method subtracts another matrix from the current matrix, element-wise. The result is a new matrix where each element is the difference of the corresponding elements in the two matrices. If the dimensions of the two matrices do not match, an [`IllegalArgumentException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang") is thrown.
        
        Example usage:
        
         Matrix matrix1 = new Matrix<>(3, 3);
         Matrix matrix2 = new Matrix<>(3, 3);
         // Assume matrix1 and matrix2 are initialized with Double values
         Matrix result = matrix1.minus(matrix2);
         // result now contains the difference of matrix1 and matrix2,
         // element-wise
         
        
        Parameters:
        
        `B` - The matrix to be subtracted from the current matrix.
        
        Returns:
        
        A new matrix object that is the result of subtracting the input matrix from the current matrix.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - If the dimensions of the two matrices do not match.
        
    *   ### times
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> times([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> B)
        
        Multiplies the current matrix by another matrix.
        
        This method multiplies the current matrix by another matrix, returning the product as a new matrix. The operation is matrix multiplication, following the rules of linear algebra.
        
        Example usage:
        
         Matrix matrix1 = new Matrix<>(3, 3);
         Matrix matrix2 = new Matrix<>(3, 3);
         // Assume matrix1 and matrix2 are initialized with Double values
         Matrix result = matrix1.times(matrix2);
         // result now contains the product of matrix1 and matrix2
         
        
        Parameters:
        
        `B` - The matrix to multiply by.
        
        Returns:
        
        The product of the two matrices.
        
    *   ### multiply
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> multiply([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> B)
        
        Multiplies the current matrix by another matrix.
        
        This method is an alias for [`times(Matrix<T> B)`](#times(ci.abidjan.adi.core.JAGMA.Matrix)) and performs the same operation as [`times(Matrix<T> B)`](#times(ci.abidjan.adi.core.JAGMA.Matrix)), multiplying the current matrix by another matrix, returning the product as a new matrix.
        
        Parameters:
        
        `B` - The matrix to multiply by.
        
        Returns:
        
        The product of the two matrices.
        
    *   ### dotProduct
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> dotProduct([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> vector)
        
        Computes the dot product (inner product) of the current matrix and a vector.
        
        This method computes the dot product of the current matrix and a vector. The dot product is a scalar value that results from an operation that combines the elements of the matrix and the vector. The dimensions of the matrix and the vector must be compatible for the operation to be valid.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         Matrix vector = new Matrix<>(3, 1);
         // Assume matrix and vector are initialized with Double values
         Matrix result = matrix.dotProduct(vector);
         // result now contains the dot product of matrix and vector
         
        
        Parameters:
        
        `vector` - The vector to compute the dot product with.
        
        Returns:
        
        The dot product of the matrix and the vector.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if the dimensions of the matrix and the vector are not compatible for the dot product operation.
        
    *   ### dotProduct
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> dotProduct([T](Matrix.html "type parameter in Matrix") scalar)
        
        Multiplies each element of this matrix by the specified scalar value.
        
        This method multiplies each element of the current matrix by a scalar value, returning a new matrix with the result. The scalar value is applied to each element individually.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix result = matrix.dotProduct(2.0);
         // result now contains the original matrix with each element multiplied
         // by 2.0
         
        
        Parameters:
        
        `scalar` - the scalar value to multiply each element of the matrix by
        
        Returns:
        
        a new Matrix instance with each element multiplied by the scalar
        
        Throws:
        
        `[ClassCastException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/ClassCastException.html "class or interface in java.lang")` - if the scalar value cannot be converted to the type T
        
    *   ### dotProduct
        
        public [T](Matrix.html "type parameter in Matrix") dotProduct([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> vector1, [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> vector2) throws [UnsupportedOperationException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/UnsupportedOperationException.html "class or interface in java.lang"), [ArithmeticException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/ArithmeticException.html "class or interface in java.lang")
        
        Calculates the dot product between two column vectors of the same length.
        
        This method calculates the dot product between two column vectors, which are matrices with a single column. The dot product is a scalar value that results from the multiplication of the corresponding elements of the two vectors.
        
        Example usage:
        
         Matrix vector1 = new Matrix<>(1, 3);
         Matrix vector2 = new Matrix<>(1, 3);
         // Assume vector1 and vector2 are initialized with Double values
         Double result = matrix.dotProduct(vector1, vector2);
         // result now contains the dot product of vector1 and vector2
         
        
        Type Parameters:
        
        `T` - the type of the elements in the vectors, must extend Number
        
        Parameters:
        
        `vector1` - the first column vector
        
        `vector2` - the second column vector
        
        Returns:
        
        the dot product between the two vectors
        
        Throws:
        
        `[UnsupportedOperationException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/UnsupportedOperationException.html "class or interface in java.lang")` - if the vectors have different lengths or are not column vectors
        
        `[ArithmeticException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/ArithmeticException.html "class or interface in java.lang")` - if any arithmetic operation results in an overflow or underflow
        
    *   ### oppose
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> oppose()
        
        Computes the opposite (negation) of each element in the matrix.
        
        This method creates a new matrix where each element is the negation of the corresponding element in the original matrix. The operation is element-wise, meaning that each element in the matrix is negated individually.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix result = matrix.oppose();
         // result now contains the negation of each element in the original
         // matrix
         
        
        Returns:
        
        The matrix with each element negated.
        
    *   ### fasttranspose
        
        public final [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> fasttranspose()
        
        Performs the transpose of the matrix in an optimized way.
        
        This method efficiently transposes the matrix, swapping its rows with its columns. The transpose operation is a fundamental operation in linear algebra that involves flipping a matrix over its diagonal. This method is optimized for performance.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix result = matrix.fasttranspose();
         // result now contains the transposed matrix
         
        
        Returns:
        
        The transposed matrix.
        
    *   ### transpose
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> transpose()
        
        Performs the transpose of the matrix in a naive way.
        
        This method naively transposes the matrix, swapping its rows with its columns. The transpose operation is a fundamental operation in linear algebra that involves flipping a matrix over its diagonal. This method is straightforward but may be less efficient than optimized methods.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix result = matrix.transpose();
         // result now contains the transposed matrix
         
        
        Returns:
        
        The transposed matrix.
        
    *   ### divElementByElement
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> divElementByElement([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> B)
        
        Divides each element of the current matrix by the corresponding element of another matrix.
        
        This method performs element-wise division of the current matrix by another matrix. Each element in the current matrix is divided by the corresponding element in the other matrix. The result is a new matrix with the division results.
        
        Example usage:
        
         Matrix matrix1 = new Matrix<>(3, 3);
         Matrix matrix2 = new Matrix<>(3, 3);
         // Assume matrix1 and matrix2 are initialized with Double values
         Matrix result = matrix1.divElementByElement(matrix2);
         // result now contains the element-wise division of matrix1 by matrix2
         
        
        Parameters:
        
        `B` - The right operand matrix.
        
        Returns:
        
        The result matrix after element-wise division.
        
    *   ### divElementByConstant
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> divElementByConstant([T](Matrix.html "type parameter in Matrix") scalar)
        
        Divides each element of the current matrix by a scalar value.
        
        This method performs element-wise division of the current matrix by a scalar value. Each element in the current matrix is divided by the scalar value. The result is a new matrix with the division results.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix result = matrix.divElementByConstant(2.0);
         // result now contains the original matrix with each element divided by
         // 2.0
         
        
        Parameters:
        
        `scalar` - The constant scalar value.
        
        Returns:
        
        The result matrix after element-wise division by the scalar.
        
    *   ### elementWiseDivide
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> elementWiseDivide([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> other)
        
        Performs element-wise division of the current matrix by another matrix.
        
        This method divides each element of the current matrix by the corresponding element of another matrix. The dimensions of the two matrices must match, otherwise an [`IllegalArgumentException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang") is thrown.
        
        Example usage:
        
         Matrix matrix1 = new Matrix<>(3, 3);
         Matrix matrix2 = new Matrix<>(3, 3);
         // Assume matrix1 and matrix2 are initialized with Double values
         Matrix result = matrix1.elementWiseDivide(matrix2);
         // result now contains the element-wise division of matrix1 by matrix2
         
        
        Parameters:
        
        `other` - The matrix to divide by.
        
        Returns:
        
        The resulting matrix after element-wise division.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - If the dimensions of the two matrices do not match.
        
    *   ### elementWiseMultiply
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> elementWiseMultiply([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> other)
        
        Performs element-wise multiplication of the current matrix by another matrix.
        
        This method multiplies each element of the current matrix by the corresponding element of another matrix. The dimensions of the two matrices must match, otherwise an [`IllegalArgumentException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang") is thrown.
        
        Example usage:
        
         Matrix matrix1 = new Matrix<>(3, 3);
         Matrix matrix2 = new Matrix<>(3, 3);
         // Assume matrix1 and matrix2 are initialized with Double values
         Matrix result = matrix1.elementWiseMultiply(matrix2);
         // result now contains the element-wise multiplication of matrix1 and
         // matrix2
         
        
        Parameters:
        
        `other` - The matrix to multiply by.
        
        Returns:
        
        The resulting matrix after element-wise multiplication.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - If the dimensions of the two matrices do not match.
        
    *   ### elementWiseAdd
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> elementWiseAdd([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> other)
        
        Performs element-wise addition of the current matrix by another matrix.
        
        This method adds each element of the current matrix to the corresponding element of another matrix. The dimensions of the two matrices must match, otherwise an [`IllegalArgumentException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang") is thrown.
        
        Example usage:
        
         Matrix matrix1 = new Matrix<>(3, 3);
         Matrix matrix2 = new Matrix<>(3, 3);
         // Assume matrix1 and matrix2 are initialized with Double values
         Matrix result = matrix1.elementWiseAdd(matrix2);
         // result now contains the element-wise addition of matrix1 and matrix2
         
        
        Parameters:
        
        `other` - The matrix to add.
        
        Returns:
        
        The resulting matrix after element-wise addition.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - If the dimensions of the two matrices do not match.
        
    *   ### elementWiseSubtract
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> elementWiseSubtract([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> other)
        
        Performs element-wise subtraction of the current matrix by another matrix.
        
        This method subtracts each element of the current matrix from the corresponding element of another matrix. The dimensions of the two matrices must match, otherwise an [`IllegalArgumentException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang") is thrown.
        
        Example usage:
        
         Matrix matrix1 = new Matrix<>(3, 3);
         Matrix matrix2 = new Matrix<>(3, 3);
         // Assume matrix1 and matrix2 are initialized with Double values
         Matrix result = matrix1.elementWiseSubtract(matrix2);
         // result now contains the element-wise subtraction of matrix1 and
         // matrix2
         
        
        Parameters:
        
        `other` - The matrix to subtract.
        
        Returns:
        
        The resulting matrix after element-wise subtraction.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - If the dimensions of the two matrices do not match.
        
    *   ### lpNorm
        
        public double lpNorm(int p)
        
        Computes the lp-norm of the matrix.
        
        The lp-norm, also known as the L^p norm, is a measure of the magnitude of the matrix. It is calculated by summing the absolute values of the matrix elements raised to the power of p, and then taking the p-th root of the sum.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         double norm = matrix.lpNorm(2); // Computes the L^2 norm (Euclidean norm)
         
        
        Parameters:
        
        `p` - the order of the norm (p >= 1)
        
        Returns:
        
        the lp-norm
        
    *   ### l1Norm
        
        public double l1Norm()
        
        Computes the l1-norm (Manhattan norm) of the matrix.
        
        The l1-norm is a measure of the magnitude of the matrix, calculated as the sum of the absolute values of its elements.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         double norm = matrix.l1Norm(); // Computes the L^1 norm (Manhattan norm)
         
        
        Returns:
        
        the l1-norm
        
    *   ### l2Norm
        
        public double l2Norm()
        
        Computes the l2-norm (Euclidean norm) of the matrix.
        
        The l2-norm is a measure of the magnitude of the matrix, calculated as the square root of the sum of the squares of its elements.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         double norm = matrix.l2Norm(); // Computes the L^2 norm (Euclidean norm)
         
        
        Returns:
        
        the l2-norm
        
    *   ### infinityNorm
        
        public double infinityNorm()
        
        Computes the infinity-norm of the matrix.
        
        The infinity-norm is the maximum absolute value of the elements in the matrix. It is a measure of the magnitude of the matrix.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         double norm = matrix.infinityNorm(); // Computes the infinity-norm
         
        
        Returns:
        
        the infinity-norm
        
    *   ### frobeniusNorm
        
        public double frobeniusNorm()
        
        Computes the Frobenius norm of the matrix.
        
        The Frobenius norm is the square root of the sum of the squares of the elements of the matrix. It is a measure of the magnitude of the matrix.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         double norm = matrix.frobeniusNorm(); // Computes the Frobenius norm
         
        
        Returns:
        
        the Frobenius norm
        
    *   ### pq\_norm
        
        public double pq\_norm(int p, int q)
        
        Computes the pq-norm of the matrix.
        
        The pq-norm is a generalization of the lp-norm, where the matrix is first transformed into a p-norm along the columns, and then the q-norm of the transformed matrix is calculated.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         double norm = matrix.pq\_norm(2, 2); // Computes the 2,2-norm
         
        
        Parameters:
        
        `p` - the order of the norm along the columns (p >= 1)
        
        `q` - the order of the norm of the transformed matrix (q >= 1)
        
        Returns:
        
        the pq-norm
        
    *   ### maxAbsValue
        
        public double maxAbsValue()
        
        Computes the maximum absolute value of the matrix.
        
        This method finds the maximum absolute value among all the elements of the matrix.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         double maxAbs = matrix.maxAbsValue(); // Finds the maximum absolute value in the matrix
         
        
        Returns:
        
        the maximum absolute value
        
    *   ### kyFanNorm
        
        public double kyFanNorm(int k) throws [IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")
        
        Calculates the Ky Fan k-norm of the matrix.
        
        The Ky Fan k-norm is the sum of the k largest singular values of the matrix. It is a measure of the matrix's "spread" or "width" in its singular value decomposition.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         double kyFanNorm = matrix.kyFanNorm(2); // Computes the 2-norm of the matrix's singular values
         
        
        Parameters:
        
        `k` - The number of largest singular values to sum.
        
        Returns:
        
        The Ky Fan k-norm of the matrix.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if k is less than 1 or greater than the number of singular values.
        
    *   ### trace
        
        public double trace()
        
        Computes the trace of the matrix.
        
        The trace of a matrix is the sum of the elements on its main diagonal. It is a measure of the matrix's "identity" or "self".
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         double trace = matrix.trace(); // Computes the trace of the matrix
         
        
        Returns:
        
        the trace of the matrix
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if the matrix is not square
        
    *   ### identity
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> identity(int nrows, int ncols)
        
        Creates an identity matrix with the given number of rows and columns.
        
        An identity matrix is a square matrix with ones on the main diagonal and zeros elsewhere. It is often used in linear algebra to represent the identity transformation.
        
        Example usage:
        
         Matrix identityMatrix = Matrix.identity(3, 3); // Creates a 3x3 identity matrix
         
        
        Parameters:
        
        `nrows` - Number of rows.
        
        `ncols` - Number of columns.
        
        Returns:
        
        The identity matrix.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if the number of rows and columns are not equal.
        
    *   ### identity
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> identity(int size)
        
        Creates an identity matrix with the given size.
        
        An identity matrix is a square matrix with ones on the main diagonal and zeros elsewhere. This method creates an identity matrix of the specified size, where the size is the number of rows and columns.
        
        Example usage:
        
         Matrix identityMatrix = Matrix.identity(3); // Creates a 3x3 identity matrix
         
        
        Parameters:
        
        `size` - The number of rows (and columns) for the identity matrix.
        
        Returns:
        
        The identity matrix of the given size.
        
    *   ### ones
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> ones(int nrows, int ncols)
        
        Creates a matrix with the given number of rows and columns, where all elements are ones.
        
        This method creates a matrix filled with ones, with the specified number of rows and columns.
        
        Example usage:
        
         Matrix onesMatrix = Matrix.ones(3, 3); // Creates a 3x3 matrix filled with ones
         
        
        Parameters:
        
        `nrows` - Number of rows.
        
        `ncols` - Number of columns.
        
        Returns:
        
        The matrix of ones.
        
    *   ### zeros
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> zeros(int rows, int cols)
        
        Creates a matrix filled with zeros.
        
        This method creates a matrix of the specified dimensions, with all elements set to zero.
        
        Example usage:
        
         Matrix zerosMatrix = Matrix.zeros(3, 3); // Creates a 3x3 matrix filled with zeros
         
        
        Parameters:
        
        `rows` - The number of rows for the matrix.
        
        `cols` - The number of columns for the matrix.
        
        Returns:
        
        A matrix filled with zeros.
        
    *   ### eye
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> eye(int size)
        
        Creates a square identity matrix of a given size.
        
        This method is an alias for [`identity(int size)`](#identity(int)) and serves the same purpose, creating a square identity matrix of the specified size.
        
        Example usage:
        
         Matrix identityMatrix = Matrix.eye(3); // Creates a 3x3 identity matrix
         
        
        Parameters:
        
        `size` - The number of rows (and columns) for the identity matrix.
        
        Returns:
        
        An identity matrix of the given size.
        
    *   ### minor
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> minor(int r, int c)
        
        Calculates the minor of a matrix.
        
        The minor of a matrix is the determinant of the submatrix obtained by removing a specified row and column from the original matrix. This method calculates the minor by excluding the given row and column from the matrix.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix minorMatrix = matrix.minor(0, 0); // Calculates the minor excluding row 0 and column 0
         
        
        Parameters:
        
        `r` - The row to exclude.
        
        `c` - The column to exclude.
        
        Returns:
        
        The minor of the matrix obtained by excluding the given row and column.
        
    *   ### toUpperTriangular
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> toUpperTriangular()
        
        Converts the current matrix into an upper (superior) triangular matrix.
        
        This method transforms the current matrix into an upper triangular matrix by setting all elements below the main diagonal to zero.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix upperTriangularMatrix = matrix.toUpperTriangular();
         
        
        Returns:
        
        The upper triangular matrix.
        
    *   ### toLowerTriangular
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> toLowerTriangular()
        
        Converts the current matrix into a lower (inferior) triangular matrix.
        
        This method transforms the current matrix into a lower triangular matrix by setting all elements above the main diagonal to zero.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix lowerTriangularMatrix = matrix.toLowerTriangular();
         
        
        Returns:
        
        The lower triangular matrix.
        
    *   ### lowerTriangular
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> lowerTriangular([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> B)
        
        Converts the given matrix into a lower (inferior) triangular matrix.
        
        This method transforms the given matrix into a lower triangular matrix by setting all elements above the main diagonal to zero.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix lowerTriangularMatrix = Matrix.lowerTriangular(matrix);
         
        
        Parameters:
        
        `B` - The matrix to convert.
        
        Returns:
        
        The lower triangular matrix.
        
    *   ### upperTriangular
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> upperTriangular([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> B)
        
        Converts the given matrix into an upper (superior) triangular matrix.
        
        This method transforms the given matrix into an upper triangular matrix by setting all elements below the main diagonal to zero.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix upperTriangularMatrix = Matrix.upperTriangular(matrix);
         
        
        Parameters:
        
        `B` - The matrix to convert.
        
        Returns:
        
        The upper triangular matrix.
        
    *   ### diag
        
        public [T](Matrix.html "type parameter in Matrix")\[\] diag()
        
        Retrieves the diagonal elements of the matrix.
        
        This method returns the elements on the main diagonal of the matrix. It is applicable only to square matrices.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Double\[\] diagonal = matrix.diag(); // Retrieves the diagonal elements
         
        
        Returns:
        
        A 1D array containing the diagonal elements.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if the matrix is not square.
        
    *   ### comatrix
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> comatrix()
        
        Computes the cofactor matrix (also known as the comatrix) of the matrix.
        
        The cofactor matrix is derived from the original matrix by calculating the determinant of the minor matrix for each element and then applying a sign change based on the element's position.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix cofactorMatrix = matrix.comatrix(); // Computes the cofactor matrix
         
        
        Returns:
        
        The cofactor matrix of the matrix.
        
    *   ### determinant
        
        public double determinant()
        
        Calculates the determinant of a square matrix using the minor() method for computation.
        
        This method computes the determinant of a square matrix using the recursive definition of determinant, which involves calculating the determinant of the minor matrices for each element in the first row or column.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         double determinant = matrix.determinant(); // Computes the determinant
         
        
        Returns:
        
        The determinant of the matrix.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if the matrix is not square.
        
    *   ### determinant2
        
        public double determinant2()
        
        Calculates the determinant of a square matrix without dependency on the minor() method.
        
        This method is an alternative to [`determinant()`](#determinant()) and calculates the determinant directly for matrices of size 1x1 and 2x2, or using a recursive definition for larger matrices, similar to the determinant() method but without explicitly calling the minor() method.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         double determinant = matrix.determinant2(); // Computes the determinant
         
        
        Returns:
        
        The determinant of the matrix.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if the matrix is not square.
        
    *   ### permutationMatrix
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> permutationMatrix(int size, int\[\] perm)
        
        Creates a permutation matrix of the given size, with a specified permutation of the rows.
        
        A permutation matrix is a square matrix with ones on the main diagonal and zeros elsewhere, where the position of the ones is determined by the specified permutation.
        
        Example usage:
        
         int\[\] permutation = { 2, 0, 1 };
         Matrix permutationMatrix = Matrix.permutationMatrix(3, permutation); // Creates a permutation matrix
         
        
        Parameters:
        
        `size` - The size of the matrix.
        
        `perm` - The permutation of the rows.
        
        Returns:
        
        The permutation matrix.
        
    *   ### permutationMatrix
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> permutationMatrix(int size)
        
        Creates a permutation matrix of the given size.
        
        This method creates a permutation matrix with ones on the main diagonal and zeros elsewhere, where the size is specified.
        
        Example usage:
        
         Matrix permutationMatrix = Matrix.permutationMatrix(3); // Creates a 3x3 permutation matrix
         
        
        Parameters:
        
        `size` - The size of the matrix.
        
        Returns:
        
        The permutation matrix.
        
    *   ### permutationMatrix
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> permutationMatrix(int nrows, int ncols)
        
        Creates a permutation matrix of the given dimensions.
        
        This method creates a permutation matrix with ones on the main diagonal and zeros elsewhere, where the number of rows and columns are specified.
        
        Example usage:
        
         Matrix permutationMatrix = Matrix.permutationMatrix(3, 3); // Creates a 3x3 permutation matrix
         
        
        Parameters:
        
        `nrows` - Number of rows.
        
        `ncols` - Number of columns.
        
        Returns:
        
        The permutation matrix.
        
    *   ### permutationMatrix
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> permutationMatrix()
        
        Creates a permutation matrix of the same size as the current matrix.
        
        This method creates a permutation matrix with ones on the main diagonal and zeros elsewhere, based on the dimensions of the current matrix.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix permutationMatrix = matrix.permutationMatrix(); // Creates a permutation matrix of the same size
         
        
        Returns:
        
        The permutation matrix.
        
    *   ### luDecomposition
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\>\[\] luDecomposition()
        
        Performs LU Decomposition on the current matrix.
        
        LU Decomposition decomposes a square matrix into the product of a lower triangular matrix (L) and an upper triangular matrix (U). This method returns an array containing the L and U matrices.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix\[\] luDecomposition = matrix.luDecomposition(); // Performs LU decomposition
         
        
        Returns:
        
        An array containing at position 0 the L(ower Triangular matrix) and at position 1 the U(pper Triangular matrix).
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if the matrix is not square.
        
    *   ### qrDecompositionHouseholder
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\>\[\] qrDecompositionHouseholder() throws [UnsupportedOperationException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/UnsupportedOperationException.html "class or interface in java.lang"), [ArithmeticException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/ArithmeticException.html "class or interface in java.lang")
        
        Performs QR decomposition using the Householder method.
        
        The Householder method is a more complex but generally more stable and efficient way to perform QR decomposition compared to other methods like Givens or Gram-Schmidt. This method decomposes the matrix into an orthogonal matrix Q and an upper triangular matrix R.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix\[\] qrDecomposition = matrix.qrDecompositionHouseholder(); // Performs QR decomposition using
                                                                                                                                                        // Householder
         
        
        Returns:
        
        An array containing at position 0 the Q matrix and at position 1 the R matrix.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if the matrix is not square.
        
        `[UnsupportedOperationException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/UnsupportedOperationException.html "class or interface in java.lang")` - if the operation is not supported for the given type.
        
        `[ArithmeticException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/ArithmeticException.html "class or interface in java.lang")` - if an arithmetic error occurs during the computation.
        
    *   ### qrDecompositionGivens
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\>\[\] qrDecompositionGivens() throws [UnsupportedOperationException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/UnsupportedOperationException.html "class or interface in java.lang"), [ArithmeticException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/ArithmeticException.html "class or interface in java.lang")
        
        Performs QR decomposition using the Givens method.
        
        The Givens method is a simple and intuitive method for performing QR decomposition. However, it can be numerically unstable, especially for matrices with small or large off-diagonal elements. This method decomposes the matrix into an orthogonal matrix Q and an upper triangular matrix R.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix\[\] qrDecomposition = matrix.qrDecompositionGivens(); // Performs QR decomposition using Givens
         
        
        Returns:
        
        An array containing at position 0 the Q matrix and at position 1 the R matrix.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if the matrix is not square.
        
        `[UnsupportedOperationException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/UnsupportedOperationException.html "class or interface in java.lang")` - if the operation is not supported for the given type.
        
        `[ArithmeticException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/ArithmeticException.html "class or interface in java.lang")` - if an arithmetic error occurs during the computation.
        
    *   ### qrDecompositionGramSchmidt
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\>\[\] qrDecompositionGramSchmidt() throws [UnsupportedOperationException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/UnsupportedOperationException.html "class or interface in java.lang"), [ArithmeticException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/ArithmeticException.html "class or interface in java.lang")
        
        Performs QR decomposition using the Gram-Schmidt method.
        
        The Gram-Schmidt method is a classical method for orthonormalizing a set of vectors in an inner product space, most commonly the Euclidean space R^n. It is particularly efficient for sparse matrices because it allows for early termination when processing zeros. This method decomposes the matrix into an orthogonal matrix Q and an upper triangular matrix R.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix\[\] qrDecomposition = matrix.qrDecompositionGramSchmidt(); // Performs QR decomposition using
                                                                                                                                                        // Gram-Schmidt
         
        
        Returns:
        
        An array containing at position 0 the Q matrix and at position 1 the R matrix.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if the matrix is not square.
        
        `[UnsupportedOperationException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/UnsupportedOperationException.html "class or interface in java.lang")` - if the operation is not supported for the given type.
        
        `[ArithmeticException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/ArithmeticException.html "class or interface in java.lang")` - if an arithmetic error occurs during the computation.
        
    *   ### backSubstitution
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> backSubstitution([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> A, [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> b)
        
        Performs back substitution on the given upper triangular matrix and right-hand side vector.
        
        Back substitution is used to solve systems of linear equations that have been transformed into an upper triangular form. This method iterates from the bottom row up, solving for each variable in turn.
        
        Example usage:
        
         Matrix A = new Matrix<>(3, 3);
         Matrix b = new Matrix<>(3, 1);
         // Assume A and b are initialized with Double values
         Matrix x = Matrix.backSubstitution(A, b); // Solves for x in Ax = b
         
        
        Parameters:
        
        `A` - The upper triangular matrix.
        
        `b` - The right-hand side vector.
        
        Returns:
        
        The solution vector.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if the matrix dimensions do not match the right-hand side vector.
        
    *   ### forwardSubstitution
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> forwardSubstitution([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> A, [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> b)
        
        Performs forward substitution on the given lower triangular matrix and right-hand side vector.
        
        Forward substitution is used to solve systems of linear equations that have been transformed into a lower triangular form. This method iterates from the top row down, solving for each variable in turn.
        
        Example usage:
        
         Matrix A = new Matrix<>(3, 3);
         Matrix b = new Matrix<>(3, 1);
         // Assume A and b are initialized with Double values
         Matrix x = Matrix.forwardSubstitution(A, b); // Solves for x in Ax = b
         
        
        Parameters:
        
        `A` - The lower triangular matrix.
        
        `b` - The right-hand side vector.
        
        Returns:
        
        The solution vector.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if the matrix dimensions do not match the right-hand side vector.
        
    *   ### normalize
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> normalize([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> column)
        
        Normalizes a column vector.
        
        This method computes the norm of the given column vector and then divides each element of the vector by its norm, resulting in a unit vector (a vector of length 1) pointing in the same direction as the original vector.
        
        Example usage:
        
         Matrix column = new Matrix<>(3, 1);
         // Assume column is initialized with Double values
         Matrix normalizedColumn = matrix.normalize(column); // Normalizes the column vector
         
        
        Parameters:
        
        `column` - The column vector to normalize.
        
        Returns:
        
        The normalized column vector.
        
        Throws:
        
        `[UnsupportedOperationException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/UnsupportedOperationException.html "class or interface in java.lang")` - if the type is not supported.
        
    *   ### solveEquation
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> solveEquation([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> A, [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> b, [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html "class or interface in java.lang") decompositionMethodName, boolean substitution)
        
        Solves a system of linear equations represented by the matrix equation Ax = b.
        
        This method solves the system of linear equations by performing either QR decomposition or LU decomposition, followed by either back substitution or forward substitution, depending on the specified decomposition method and substitution flag.
        
        Example usage:
        
         Matrix A = new Matrix<>(3, 3);
         Matrix b = new Matrix<>(3, 1);
         // Assume A and b are initialized with Double values
         Matrix x = matrix.solveEquation(A, b, "QR", true); // Solves Ax = b using QR decomposition and back
                                                                                                                                // substitution
         
        
        Parameters:
        
        `A` - The matrix A in the equation.
        
        `b` - The right-hand side vector in the equation.
        
        `decompositionMethodName` - The name of the decomposition method to use ("QR" or "LU").
        
        `substitution` - true for back substitution, false for forward substitution.
        
        Returns:
        
        The solution vector x.
        
        Throws:
        
        `[UnsupportedOperationException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/UnsupportedOperationException.html "class or interface in java.lang")` - if the decomposition method is not supported.
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if the decomposition method name is invalid.
        
    *   ### InverseLU
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> InverseLU()
        
        Calculates the inverse of the matrix using LU decomposition.
        
        This method computes the inverse of the matrix by first performing LU decomposition and then solving the system of linear equations Ax = I for each column X of the inverse matrix, where A is the original matrix and I is the identity matrix.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix inverseMatrix = matrix.InverseLU(); // Computes the inverse of the matrix
         
        
        Returns:
        
        The inverse of the matrix.
        
        Throws:
        
        `[RuntimeException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/RuntimeException.html "class or interface in java.lang")` - if the matrix is not square.
        
    *   ### determinantLU
        
        public [T](Matrix.html "type parameter in Matrix") determinantLU() throws [RuntimeException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/RuntimeException.html "class or interface in java.lang")
        
        Calculates the determinant of the matrix using LU decomposition.
        
        This method computes the determinant of a square matrix by performing LU decomposition and then multiplying the diagonal elements of the upper triangular matrix from the LU decomposition.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         double determinant = matrix.determinantLU(); // Computes the determinant of the matrix
         
        
        Returns:
        
        The determinant of the matrix.
        
        Throws:
        
        `[RuntimeException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/RuntimeException.html "class or interface in java.lang")` - if the matrix is not square.
        
    *   ### choleskyLDecomposition
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> choleskyLDecomposition()
        
        Performs Cholesky L (lower triangular) decomposition on the current matrix.
        
        This method decomposes a symmetric, positive-definite matrix into the product of a lower triangular matrix L and its conjugate transpose. This decomposition is particularly efficient for numerical computation.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values and is symmetric,
         // positive-definite
         Matrix L = matrix.choleskyLDecomposition(); // Performs Cholesky L decomposition
         
        
        Returns:
        
        The lower triangular matrix L.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if the matrix is not square.
        
    *   ### choleskyDecomposition
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> choleskyDecomposition() throws [RuntimeException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/RuntimeException.html "class or interface in java.lang")
        
        Performs Cholesky decomposition on the matrix.
        
        This method decomposes a symmetric, positive-definite matrix into the product of a lower triangular matrix L and its conjugate transpose. It checks that the matrix is square, symmetric, and positive-definite before performing the decomposition.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values and is symmetric,
         // positive-definite
         Matrix L = matrix.choleskyDecomposition(); // Performs Cholesky decomposition
         
        
        Returns:
        
        The lower triangular matrix L.
        
        Throws:
        
        `[RuntimeException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/RuntimeException.html "class or interface in java.lang")` - if the matrix is not square, symmetric, or positive definite.
        
    *   ### qrDecomposition
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\>\[\] qrDecomposition()
        
        Performs QR decomposition on the matrix.
        
        This method decomposes a matrix into the product of an orthogonal matrix Q and an upper triangular matrix R. The decomposition method used is "Householder" method.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix\[\] qr = matrix.qrDecomposition("Householder"); // Performs QR decomposition using Householder
         Matrix\[\] Q = qr\[0\]; // get Q
         Matrix\[\] R = qr\[1\]; // get R
         
        
        Returns:
        
        An array of two matrices \[Q, R\] where Q is an orthogonal matrix and R is an upper triangular matrix.
        
        Throws:
        
        `[Exception](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Exception.html "class or interface in java.lang")` - if the specified decomposition method is not supported or does not exist.
        
        `[UnsupportedOperationException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/UnsupportedOperationException.html "class or interface in java.lang")` - if the matrix has more columns than rows.
        
    *   ### qrDecomposition
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\>\[\] qrDecomposition([String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html "class or interface in java.lang") qrMethodName) throws [Exception](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Exception.html "class or interface in java.lang")
        
        Performs QR decomposition on the matrix by specifying the method name to use.
        
        This method decomposes a matrix into the product of an orthogonal matrix Q and an upper triangular matrix R. The decomposition method can be specified as "Householder", "Givens", or "GramScmidt".
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix\[\] qr = matrix.qrDecomposition("Householder"); // Performs QR decomposition using Householder
         Matrix\[\] Q = qr\[0\]; // get Q
         Matrix\[\] R = qr\[1\]; // get R
         
        
        Parameters:
        
        `qrMethodName` - "Householder" | "Givens" | "GramScmidt"
        
        Returns:
        
        An array of two matrices \[Q, R\] where Q is an orthogonal matrix and R is an upper triangular matrix.
        
        Throws:
        
        `[Exception](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Exception.html "class or interface in java.lang")` - if the specified decomposition method is not supported or does not exist.
        
        `[UnsupportedOperationException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/UnsupportedOperationException.html "class or interface in java.lang")` - if the matrix has more columns than rows.
        
    *   ### inverse
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> inverse() throws [RuntimeException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/RuntimeException.html "class or interface in java.lang")
        
        Calculates the inverse of the matrix.
        
        This method computes the inverse of the matrix by augmenting the original matrix with the identity matrix and then applying Gauss-Jordan elimination to transform the left side of the augmented matrix into the identity matrix.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix inverseMatrix = matrix.inverse(); // Computes the inverse of the matrix
         
        
        Returns:
        
        The inverse of the matrix.
        
        Throws:
        
        `[RuntimeException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/RuntimeException.html "class or interface in java.lang")` - if the matrix is not square or singular.
        
    *   ### determinantByHouseholderQR
        
        public [T](Matrix.html "type parameter in Matrix") determinantByHouseholderQR() throws [RuntimeException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/RuntimeException.html "class or interface in java.lang")
        
        Calculates the determinant of the matrix using the Householder QR decomposition method.
        
        This method computes the determinant of a square matrix by first performing the Householder QR decomposition, which decomposes the matrix into an orthogonal matrix Q and an upper triangular matrix R. The determinant is then calculated by multiplying the diagonal elements of the upper triangular matrix R.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         double determinant = matrix.determinantByHouseholderQR(); // Computes the determinant using Householder QR
         
        
        Returns:
        
        The determinant of the matrix.
        
        Throws:
        
        `[RuntimeException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/RuntimeException.html "class or interface in java.lang")` - if the matrix is not square.
        
    *   ### determinantByQRGramSchmidt
        
        public [T](Matrix.html "type parameter in Matrix") determinantByQRGramSchmidt() throws [RuntimeException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/RuntimeException.html "class or interface in java.lang")
        
        Calculates the determinant of the matrix using the Gram-Schmidt QR decomposition method.
        
        This method computes the determinant of a square matrix by first performing the Gram-Schmidt QR decomposition, which decomposes the matrix into an orthogonal matrix Q and an upper triangular matrix R. The determinant is then calculated by multiplying the diagonal elements of the upper triangular matrix R.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         double determinant = matrix.determinantByQRGramSchmidt(); // Computes the determinant using Gram-Schmidt QR
         
        
        Returns:
        
        The determinant of the matrix.
        
        Throws:
        
        `[RuntimeException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/RuntimeException.html "class or interface in java.lang")` - if the matrix is not square.
        
    *   ### determinantByQRGivens
        
        public [T](Matrix.html "type parameter in Matrix") determinantByQRGivens() throws [RuntimeException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/RuntimeException.html "class or interface in java.lang")
        
        Calculates the determinant of the matrix using the Givens QR decomposition method.
        
        This method computes the determinant of a square matrix by first performing the Givens QR decomposition, which decomposes the matrix into an orthogonal matrix Q and an upper triangular matrix R. The determinant is then calculated by multiplying the diagonal elements of the upper triangular matrix R.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         double determinant = matrix.determinantByQRGivens(); // Computes the determinant using Givens QR
         
        
        Returns:
        
        The determinant of the matrix.
        
        Throws:
        
        `[RuntimeException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/RuntimeException.html "class or interface in java.lang")` - if the matrix is not square.
        
    *   ### inverseGaussJordan
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> inverseGaussJordan() throws [RuntimeException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/RuntimeException.html "class or interface in java.lang")
        
        Inverts the matrix using the Gauss-Jordan elimination method.
        
        This method computes the inverse of a square matrix by augmenting the original matrix with the identity matrix and then applying Gauss-Jordan elimination to transform the left side of the augmented matrix into the identity matrix.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix inverseMatrix = matrix.inverseGaussJordan(); // Computes the inverse using Gauss-Jordan
                                                                                                                                // elimination
         
        
        Returns:
        
        The inverse matrix.
        
        Throws:
        
        `[RuntimeException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/RuntimeException.html "class or interface in java.lang")` - if the matrix is not square or singular.
        
    *   ### reshape
        
        public void reshape(int newRows, int newCols) throws [RuntimeException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/RuntimeException.html "class or interface in java.lang")
        
        Reshapes the matrix to the specified number of rows and columns.
        
        This method changes the dimensions of the matrix to the specified number of rows and columns. It ensures that the total number of elements in the reshaped matrix matches the total number of elements in the original matrix. If the total number of elements does not match, a [`RuntimeException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/RuntimeException.html "class or interface in java.lang") is thrown.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         matrix.reshape(2, 2); // Reshapes the matrix to 2x2
         
        
        Parameters:
        
        `newRows` - The new number of rows.
        
        `newCols` - The new number of columns.
        
        Throws:
        
        `[RuntimeException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/RuntimeException.html "class or interface in java.lang")` - if the total number of elements in the reshaped matrix does not match the total number of elements in the original matrix.
        
    *   ### flatten
        
        public [T](Matrix.html "type parameter in Matrix")\[\] flatten()
        
        Flattens the matrix into a 1D array.
        
        This method returns the matrix's elements in a single array, effectively converting the matrix into a linearized form.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Double\[\] flattened = matrix.flatten(); // Flattens the matrix into a 1D array
         
        
        Returns:
        
        The flattened 1D array.
        
    *   ### eigenvalueDecomposition
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> eigenvalueDecomposition()
        
        Performs the Jacobi eigenvalues decomposition iterative and basic algorithm.
        
        This method computes the eigenvalues of the matrix using the Jacobi eigenvalues decomposition. It iteratively refines the matrix until convergence is reached or the maximum number of iterations is exceeded.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix eigendecomposition = matrix.eigenvalueDecomposition(); // Computes the eigenvalues
         Double\[\]\[\] eigenValuesVectorsArray = matrix.eigenvalueDecomposition().to2DArray(); // Computes the eigenvalues
         Double\[\] eigenvalues = eigenValuesVectorsArray\[0\]; // get the eigenvalues
         Double\[\] eigenvectors = eigenValuesVectorsArray\[1\]; // get the eigenvectors
         
        
        Returns:
        
        The eigenvalues as a matrix.
        
    *   ### eigenvalues
        
        public [T](Matrix.html "type parameter in Matrix")\[\] eigenvalues()
        
        Computes the eigenvalues of the matrix.
        
        This method calculates the eigenvalues of a square matrix by performing a QR decomposition and then using the diagonal elements of the resulting matrix.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Double\[\] eigenvalues = matrix.eigenvalues(); // Computes the eigenvalues
         
        
        Returns:
        
        A 1D array containing the eigenvalues.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if the matrix is not square.
        
    *   ### eigenvectors
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> eigenvectors()
        
        Computes the eigenvectors of the matrix.
        
        This method calculates the eigenvectors of a square matrix by performing a QR decomposition and then extracting the columns of the resulting matrix Q, which represent the eigenvectors.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix eigenvectors = matrix.eigenvectors(); // Computes the eigenvectors
         
        
        Returns:
        
        A 2D array where each row is an eigenvector.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if the matrix is not square.
        
    *   ### getSubMatrix
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> getSubMatrix(int endRow)
        
        Returns a subMatrix of the current matrix.
        
        This method extracts a subMatrix from the current matrix, starting from the first row and ending at the specified row. The subMatrix will have the same number of columns as the original matrix.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix subMatrix = matrix.getSubMatrix(2); // Extracts a 3x3 subMatrix ending at row 2
         
        
        Parameters:
        
        `endRow` - The row index where the subMatrix ends.
        
        Returns:
        
        The subMatrix.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if the endRow index is not valid.
        
    *   ### getSubMatrix
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> getSubMatrix(int i, int j)
        
        Returns a submatrix of this matrix, excluding the specified row and column.
        
        This method creates a new matrix by excluding the specified row and column from the original matrix. The resulting submatrix will have dimensions one less in both rows and columns than the original matrix.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix subMatrix = matrix.getSubMatrix(1, 2); // Excludes row 1 and column 2
         
        
        Parameters:
        
        `i` - the index of the row to exclude
        
        `j` - the index of the column to exclude
        
        Returns:
        
        the submatrix of this matrix, excluding the specified row and column
        
    *   ### getSubMatrix
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> getSubMatrix(int rowstart, int rowend, int colstart, int colend)
        
        Extracts a subMatrix from the original matrix, using specified start and end indices for rows and columns.
        
        This method allows for the extraction of a subMatrix from the original matrix by specifying the start and end indices for both rows and columns. The indices are 1-based, meaning the first row or column is indexed as 1.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with Double values
         Matrix subMatrix = matrix.getSubMatrix(1, 3, 1, 3); // Extracts a 2x2 subMatrix from the 1st to 3rd row
                                                                                                                                // and column
         
        
        Parameters:
        
        `rowstart` - The start index for the row (inclusive).
        
        `rowend` - The end index for the row (exclusive).
        
        `colstart` - The start index for the column (inclusive).
        
        `colend` - The end index for the column (exclusive).
        
        Returns:
        
        A new matrix object that is a subMatrix of the current matrix.
        
        Throws:
        
        `[IllegalArgumentException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalArgumentException.html "class or interface in java.lang")` - if the indices are invalid.
        
    *   ### converged
        
        public boolean converged([Number](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Number.html "class or interface in java.lang")\[\]\[\] M, double tolerance)
        
        Checks matrix convergence using off-diagonal element norm as tolerance.
        
        This method evaluates the convergence of the matrix based on the norm of its off-diagonal elements. Convergence is determined if the norm of the off-diagonal elements falls below a specified tolerance. It is based on the Jacobi eigenvalues. Choose tolerance carefully: too low => slow/no convergence, too high => inaccurate results. Experiment for optimal balance
        
        Parameters:
        
        `M` - The matrix to check for convergence.
        
        `tolerance` - The tolerance within which the norm of the off-diagonal elements should fall for convergence.
        
        Returns:
        
        true if the norm of the off-diagonal elements is less than the tolerance, false otherwise.
        
    *   ### singularValueDecompositionWithHouseholderQR
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\>\[\] singularValueDecompositionWithHouseholderQR() throws [RuntimeException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/RuntimeException.html "class or interface in java.lang")
        
        Calculates the Singular Value Decomposition (SVD) of the matrix using Householder QR decomposition.
        
        This method decomposes the matrix into three matrices U, Σ, and V^T, where U and V are orthogonal matrices and Σ is a diagonal matrix containing the singular values of the original matrix. The decomposition is performed using the Householder QR method.
        
        Returns:
        
        An array containing the U, Σ, and V^T matrices of the SVD.
        
        Throws:
        
        `[RuntimeException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/RuntimeException.html "class or interface in java.lang")` - if the matrix is not square.
        
    *   ### singularValueDecompositionWithGramSchmidtQR
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\>\[\] singularValueDecompositionWithGramSchmidtQR() throws [RuntimeException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/RuntimeException.html "class or interface in java.lang")
        
        Calculates the Singular Value Decomposition (SVD) of the matrix using Gram-Schmidt QR decomposition.
        
        This method decomposes the matrix into three matrices U, Σ, and V^T, where U and V are orthogonal matrices and Σ is a diagonal matrix containing the singular values of the original matrix. The decomposition is performed using the Gram-Schmidt QR method.
        
        Returns:
        
        An array containing the U, Σ, and V^T matrices of the SVD.
        
        Throws:
        
        `[RuntimeException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/RuntimeException.html "class or interface in java.lang")` - if the matrix is not square.
        
    *   ### singularValueDecompositionWithGivensQR
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\>\[\] singularValueDecompositionWithGivensQR() throws [RuntimeException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/RuntimeException.html "class or interface in java.lang")
        
        Calculates the Singular Value Decomposition (SVD) of the matrix using Givens QR decomposition.
        
        This method decomposes the matrix into three matrices U, Σ, and V^T, where U and V are orthogonal matrices and Σ is a diagonal matrix containing the singular values of the original matrix. The decomposition is performed using the Givens QR method.
        
        Returns:
        
        An array containing the U, Σ, and V^T matrices of the SVD.
        
        Throws:
        
        `[RuntimeException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/RuntimeException.html "class or interface in java.lang")` - if the matrix is not square.
        
    *   ### singularValues
        
        public double\[\] singularValues() throws [RuntimeException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/RuntimeException.html "class or interface in java.lang")
        
        Method. Calculate the singular values of a matrix.
        
        Returns:
        
        The singular values of the matrix.
        
        Throws:
        
        `[RuntimeException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/RuntimeException.html "class or interface in java.lang")` - if the matrix is not square.
        
    *   ### singularValues
        
        public double\[\] singularValues([String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html "class or interface in java.lang") decompositionMethodName) throws [RuntimeException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/RuntimeException.html "class or interface in java.lang")
        
        Calculates the singular values of a matrix using a specified decomposition method.
        
        This method calculates the singular values of the matrix by performing a Singular Value Decomposition (SVD) using the specified decomposition method. The supported methods include "Householder", "GramSchmidt", and "Givens".
        
        Parameters:
        
        `decompositionMethodName` - The name of the decomposition method to use.
        
        Returns:
        
        An array of double values representing the singular values of the matrix.
        
        Throws:
        
        `[RuntimeException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/RuntimeException.html "class or interface in java.lang")` - if the matrix is not square or if an invalid decomposition method name is provided.
        
    *   ### adjugate
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> adjugate()
        
        Calculates the adjugate of the matrix. The adjugate of a matrix is the transpose of the cofactor matrix.
        
        This method computes the adjugate by calculating the cofactor for each element, applying the appropriate sign, and then transposing the resulting matrix. The adjugate is defined for square matrices only.
        
        Returns:
        
        The adjugate Matrix.
        
        Throws:
        
        `[IllegalStateException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/IllegalStateException.html "class or interface in java.lang")` - if the matrix is not square.
        
    *   ### power
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> power(int exponent) throws [UnsupportedOperationException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/UnsupportedOperationException.html "class or interface in java.lang")
        
        Raises the matrix to the power of an exponent.
        
        This method calculates the matrix raised to a specified exponent. For square matrices, it supports both positive and negative exponents. The method first checks if the matrix is square, then calculates the identity matrix of the same size and raises it to the specified exponent by multiplying it by the original matrix.
        
        Parameters:
        
        `exponent` - The exponent to which the matrix is raised.
        
        Returns:
        
        The matrix raised to the specified power.
        
        Throws:
        
        `[UnsupportedOperationException](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/UnsupportedOperationException.html "class or interface in java.lang")` - if the matrix is not square.
        
    *   ### solve
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> solve([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> b)
        
        Solves equations by nonsingular systems and least squares.
        
        This method solves a system of linear equations or performs a least squares approximation using LU decomposition. It first decomposes the matrix into lower and upper triangular matrices (L and U) and then solves the system by forward and backward substitution.
        
        Parameters:
        
        `b` - The right-hand side of the equation.
        
        Returns:
        
        The solution matrix.
        
    *   ### conditionNumber
        
        public double conditionNumber()
        
        Calculates the condition number of the matrix.
        
        The condition number of a matrix is the ratio of the largest singular value to the smallest singular value. It provides a measure of how sensitive the solution of the system of equations is to small changes in the coefficients.
        
        Returns:
        
        The condition number of the matrix.
        
    *   ### rank
        
        public int rank()
        
        Calculates the rank of the matrix.
        
        The rank of a matrix is the maximum number of linearly independent rows (or columns). It can be found as the number of nonzero singular values. This method uses the singular values of the matrix to determine its rank.
        
        Returns:
        
        The rank of the matrix.
        
    *   ### pseudoinverse
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> pseudoinverse()
        
        Calculates the pseudoinverse of the matrix.
        
        The pseudoinverse of a matrix A, denoted A⁺, is a generalization of the inverse matrix. It is defined and unique for all matrices whose entries are real or complex numbers. This method computes the pseudoinverse by performing a singular value decomposition (SVD) and then calculating the pseudoinverse of the singular values.
        
        Returns:
        
        The pseudoinverse of the matrix.
        
    *   ### conjugate
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> conjugate()
        
        Returns the conjugate of this matrix.
        
        The conjugate of a matrix is obtained by taking the complex conjugate of each element. For matrices with real elements, the conjugate matrix is identical to the original matrix. For complex matrices, this method applies the complex conjugate operation to each element, resulting in a new matrix where each complex number is replaced by its complex conjugate.
        
        Example usage:
        
         Matrix matrix = new Matrix<>(2, 2);
         // Assume matrix is initialized with Complex values
         Matrix conjugateMatrix = matrix.conjugate();
         
        
        Returns:
        
        The conjugate of this matrix.
        
    *   ### getConjugateTranspose
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> getConjugateTranspose()
        
        Returns the conjugate transpose of the matrix.
        
        The conjugate transpose of a matrix is obtained by taking the transpose and then taking the complex conjugate of each element. For real matrices, the transpose and conjugate transpose are the same.
        
        Returns:
        
        The conjugate transpose of the matrix.
        
    *   ### strassen
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> strassen([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> B)
        
        Performs Strassen's matrix multiplication algorithm.
        
        This method implements Strassen's algorithm for matrix multiplication, which is a divide-and-conquer algorithm that recursively breaks down the input matrices into smaller sub-matrices and combines the results to obtain the product. This method is efficient for large matrices and takes advantage of the principle of parallelism in the computation.
        
        Example usage:
        
             Matrix A = new Matrix<>(3, 3);
             Matrix B = new Matrix<>(3, 3);
             // Assume A and B are initialized with values
             Matrix C = A.strassen(B);
         
        
        Parameters:
        
        `B` - The matrix to multiply with the current matrix.
        
        Returns:
        
        The product of the current matrix and matrix B, computed using Strassen's algorithm.
        
    *   ### strassenMultiply
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> strassenMultiply([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> A, [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> B)
        
        Performs Strassen's matrix multiplication algorithm on two matrices.
        
        This method implements a generalization of Strassen's algorithm for matrix multiplication, taking two matrices as input and returning their product. The algorithm is optimized for large matrices by partitioning them into smaller sub-matrices and performing the multiplication in a divide-and-conquer fashion. This method ensures that the matrix multiplication is performed efficiently, leveraging the parallelism inherent in Strassen's algorithm.
        
        Example usage:
        
             Matrix A = new Matrix<>(3, 3);
             Matrix B = new Matrix<>(3, 3);
             // Assume A and B are initialized with values
             Matrix C = Matrix.strassenMultiply(A, B);
         
        
        Parameters:
        
        `A` - The first matrix to multiply.
        
        `B` - The second matrix to multiply.
        
        Returns:
        
        The product of matrices A and B, computed using Strassen's algorithm.
        
    *   ### partition
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\>\[\]\[\] partition([Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\> B)
        
        Partitions the given matrix into four equal-sized sub-matrices.
        
        This method divides the input matrix into four sub-matrices of equal size, assuming the matrix is square and its dimensions are evenly divisible by 2. The method returns a 2D array containing the four sub-matrices, arranged in a 2x2 grid. This partitioning is a crucial step in the Strassen algorithm, as it allows for the recursive application of the algorithm to the sub-matrices.
        
        Example usage:
        
             Matrix B = new Matrix<>(4, 4);
             // Assume B is initialized with values
             Matrix\[\]\[\] partitions = B.partition();
         
        
        Parameters:
        
        `B` - The matrix to partition.
        
        Returns:
        
        A 2D array containing the four sub-matrices of B.
        
    *   ### partition
        
        public [Matrix](Matrix.html "class in ci.abidjan.adi.core.JAGMA")<[T](Matrix.html "type parameter in Matrix")\>\[\]\[\] partition()
        
        Partitions this matrix into four equal-sized sub-matrices.
        
        This method is a convenience method that partitions the current matrix into four equal-sized sub-matrices. It assumes that the matrix is square and its dimensions are evenly divisible by 2. The method returns a 2D array containing the four sub-matrices, arranged in a 2x2 grid. This partitioning is a crucial step in the Strassen algorithm, as it allows for the recursive application of the algorithm to the sub-matrices.
        
        Returns:
        
        A 2D array containing the four sub-matrices of the current matrix.
        
    *   ### isPositiveDefinite
        
        public boolean isPositiveDefinite()
        
        Method. Check if this matrix is positive definite. A matrix is positive definite if it satisfies the following conditions: 1. The matrix is square (i.e. has the same number of rows and columns) 2. The matrix is symmetric (i.e. equal to its own transpose) 3. All the eigenvalues of the matrix are positive
        
        Returns:
        
        true if the matrix is positive definite, false otherwise
        
    *   ### isMetricPositiveDefined
        
        public boolean isMetricPositiveDefined()
        
        Method. Check if this matrix satisfies the metric property. A matrix satisfies the metric property if it is positive definite and all its diagonal elements are equal to 1.
        
        Returns:
        
        true if the matrix satisfies the metric property, false otherwise
        
    *   ### isDiagonal2
        
        public boolean isDiagonal2()
        
        Method. Return true if the matrix is diagonal, false otherwise. A matrix is diagonal if all non-diagonal elements are zero.
        
        Returns:
        
        true if the matrix is diagonal, false otherwise
        
    *   ### isIdentity2
        
        public boolean isIdentity2()
        
        Method. Return true if the matrix is an identity matrix, false otherwise. An identity matrix is a square matrix with ones on the main diagonal and zeros elsewhere.
        
        Returns:
        
        true if the matrix is an identity matrix, false otherwise
        
    *   ### isOrthogonal2
        
        public boolean isOrthogonal2()
        
        Method. Return true if the matrix is orthogonal, false otherwise. A matrix is orthogonal if its transpose is equal to its inverse.
        
        Returns:
        
        true if the matrix is orthogonal, false otherwise
        
    *   ### isUpperTriangular2
        
        public boolean isUpperTriangular2()
        
        Method. Return true if the matrix is upper triangular, false otherwise. An upper triangular matrix is a matrix where all elements below the main diagonal are zero.
        
        Returns:
        
        true if the matrix is upper triangular, false otherwise
        
    *   ### isLowerTriangular2
        
        public boolean isLowerTriangular2()
        
        Method. Return true if the matrix is lower triangular, false otherwise. A lower triangular matrix is a matrix where all elements above the main diagonal are zero.
        
        Returns:
        
        true if the matrix is lower triangular, false otherwise
        
    *   ### isEmpty
        
        public boolean isEmpty()
        
        Method. Check if the matrix is empty or not.
        
        Returns:
        
        true if the matrix is empty, false otherwise
        
    *   ### isDiagonal
        
        public boolean isDiagonal()
        
        Method. Check if the matrix is diagonal or not.
        
        Returns:
        
        true if the matrix is diagonal, false otherwise
        
    *   ### isIdentity
        
        public boolean isIdentity()
        
        Method. Check if the matrix is identity or not.
        
        Returns:
        
        true if the matrix is identity, false otherwise
        
    *   ### isOrthogonal
        
        public boolean isOrthogonal()
        
        Method. Check if the matrix is orthogonal or not.
        
        Returns:
        
        true if the matrix is orthogonal, false otherwise
        
    *   ### isUpperTriangular
        
        public boolean isUpperTriangular()
        
        Method. Check if the matrix is upper triangular or not.
        
        Returns:
        
        true if the matrix is upper triangular, false otherwise
        
    *   ### isLowerTriangular
        
        public boolean isLowerTriangular()
        
        Method. Check if the matrix is lower triangular or not.
        
        Returns:
        
        true if the matrix is lower triangular, false otherwise
        
    *   ### isSymmetric
        
        public boolean isSymmetric()
        
        Method. Check if the matrix is symmetric or not.
        
        Returns:
        
        true if the matrix is symmetric, false otherwise
        
    *   ### isSymmetricPositiveDefined
        
        public boolean isSymmetricPositiveDefined()
        
        Checks if the matrix is symmetric and positive definite.
        
        This method verifies whether the matrix is both symmetric and positive definite. A matrix is symmetric if it is equal to its transpose, and positive definite if all its principal minors are positive.
        
        Returns:
        
        true if the matrix is symmetric and positive definite, false otherwise.
        
    *   ### isHermitian
        
        public boolean isHermitian()
        
        Method. Check if the matrix is Hermitian or not.
        
        Returns:
        
        true if the matrix is Hermitian, false otherwise
        
    *   ### isSquare
        
        public boolean isSquare()
        
        Method. Check if the matrix is square or not.
        
        Returns:
        
        true if the matrix is square, false otherwise
        
    *   ### isNonsingular
        
        public boolean isNonsingular()
        
        Method. Return true if the matrix is nonsingular, false otherwise. A matrix is nonsingular if its determinant is not zero.
        
    *   ### isSingular
        
        public boolean isSingular()
        
        Method. Return true if the matrix is singular, false otherwise. A matrix is singular if its determinant is zero.
        
        A matrix is singular if it does not have an inverse, meaning its determinant is zero. Singular matrices do not have a multiplicative inverse and cannot be used to solve systems of linear equations.
        
        Example usage:
        
         Matrix<Double> matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with values
         boolean isSingular = matrix.isSingular();
         if (isSingular) {
                System.out.println("The matrix is singular.");
         } else {
                System.out.println("The matrix is regular.");
         }
         
        
        Returns:
        
        `true` if the matrix is singular, `false` otherwise.
        
    *   ### isFullRank
        
        public boolean isFullRank()
        
        Method .Return true if the matrix is full rank, false otherwise. A matrix is full rank if its rank is equal to the minimum of its number of rows and columns.
        
    *   ### isColumnSpaceOnly
        
        public boolean isColumnSpaceOnly()
        
        Method. Check if the columns of this matrix span only the column space.
        
        Returns:
        
        true if the columns span only the column space, false otherwise
        
    *   ### isRowSpaceOnly
        
        public boolean isRowSpaceOnly()
        
        Method. Check if the rows of this matrix span only the row space.
        
        Returns:
        
        true if the rows span only the row space, false otherwise
        
    *   ### isLinearlyDependent
        
        public boolean isLinearlyDependent()
        
        Method. Check if the vectors in this matrix are linearly dependent.
        
        Returns:
        
        true if the vectors are linearly dependent, false otherwise
        
    *   ### isLinearlyIndependent
        
        public boolean isLinearlyIndependent()
        
        Method. Check if the vectors in this matrix are linearly independent.
        
        Returns:
        
        true if the vectors are linearly independent, false otherwise
        
    *   ### isNormal
        
        public boolean isNormal()
        
        Method. Checks if the matrix is normal.
        
        A matrix is considered normal if it commutes with its conjugate transpose. In other words, for a matrix A, A must be equal to its conjugate transpose A\* when multiplied by A and A\* by A.
        
        Example usage:
        
         Matrix<Double> matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with values
         boolean isNormal = matrix.isNormal();
         if (isNormal) {
                System.out.println("The matrix is normal.");
         } else {
                System.out.println("The matrix is not normal.");
         }
         
        
        Returns:
        
        `true` if the matrix is normal, `false` otherwise.
        
    *   ### isRegular
        
        public boolean isRegular()
        
        Checks if the matrix is regular (non-singular).
        
        A matrix is regular if it has an inverse, meaning its determinant is not zero. A regular matrix can be multiplied by its inverse to produce the identity matrix.
        
        Example usage:
        
         Matrix<Double> matrix = new Matrix<>(3, 3);
         // Assume matrix is initialized with values
         boolean isRegular = matrix.isRegular();
         if (isRegular) {
                System.out.println("The matrix is regular.");
         } else {
                System.out.println("The matrix is singular.");
         }
         
        
        Returns:
        
        `true` if the matrix is regular (non-singular), `false` otherwise.
        
    *   ### toString
        
        public [String](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html "class or interface in java.lang") toString()
        
        OTHER USEFUL METHODS
        
        Overrides:
        
        `[toString](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Object.html#toString() "class or interface in java.lang")` in class `[Object](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Object.html "class or interface in java.lang")`
        
    *   ### equals
        
        public boolean equals([Object](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Object.html "class or interface in java.lang") o)
        
        Overrides:
        
        `[equals](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Object.html#equals(java.lang.Object) "class or interface in java.lang")` in class `[Object](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Object.html "class or interface in java.lang")`
        
    *   ### print
        
        public void print()
        
        Method. Print the matrix to the standard output in a formatted manner.
        
        As a nice way to print a multidimensional array, This method converts the matrix to a 2D array and then formats the output string to make it more readable. It replaces occurrences of "\], \[" with a newline character to separate rows and removes unnecessary brackets and spaces to clean up the output.
        
        Example usage:
        
         GenericMatrix<Double> matrix = new GenericMatrix<>(3, 3);
         // Assume matrix is initialized with values
         matrix.print();
         
        
    *   ### printAsMatlab
        
        public void printAsMatlab()
        
        Method. Print the matrix in a style resembling MATLAB's matrix printing.
        
        This method converts the matrix to a 2D array and formats the output string to mimic MATLAB's matrix printing style. It replaces occurrences of "\], \[" with "\]\\n" to separate rows and adjusts the brackets to closely match MATLAB's output format.
        
        Example usage:
        
         GenericMatrix<Double> matrix = new GenericMatrix<>(3, 3);
         // Assume matrix is initialized with values
         matrix.printAsMatlab();
