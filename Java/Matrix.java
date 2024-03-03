package ci.abidjan.adi.JAGMA;

/**
 * Copyright (C) 2018-2024 Junior ADI
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/>.
 */
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

/**
 * <b>The Java Generic Matrix</b> Class also known as <b>JAGMA</b> is a matrix class that supports integers, floats, and doubles. 
 * It represents a dense rectangular matrix with generic element type T. This
 * implementation uses a contiguous block of memory to store all elements (1D array for performance).
 *
 * @param <T> Type parameter representing the element type (should extend Number).
 */
public class Matrix<T extends Number> {

	/**
	 * The 1-D internal array to create the matrix in the mathematical sense.
	 */
	private T[] array;

	/**
	 * The number of rows.
	 */
	private int nrows;

	/**
	 * The number of columns.
	 */
	private int ncols;

	/* CONSTRUCTORS */

	/**
	 * Default constructor for the Matrix class.
	 * <p>
	 * This constructor is provided for scenarios where specific initialization
	 * methods are not required. It does not perform any initialization of the
	 * matrix elements, leaving the matrix in an uninitialized state.
	 * </p>
	 * <p>
	 * This constructor is particularly useful when creating an instance of the
	 * Matrix class and then immediately initializing it with specific dimensions
	 * and values through other methods, such as {@link #setDimensions(int, int)}
	 * and {@link #setValues(T[])}.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Integer> matrix = new Matrix<>();
	 * matrix.setDimensions(3, 3);
	 * matrix.setValues(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 });
	 * </pre>
	 * </p>
	 */
	public Matrix() {
	}

	/**
	 * Constructor. Create a matrix from a 1D array with specified dimensions.
	 * <p>
	 * This constructor initializes a matrix with the specified number of rows and
	 * columns, and populates it with the elements from the provided 1D array. The
	 * array should contain the matrix elements in row-major order, meaning that the
	 * elements of each row are stored consecutively in the array.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Integer[] array = { 1, 2, 3, 4, 5, 6 };
	 * Matrix<Integer> matrix = new Matrix<>(2, 3, array);
	 * 
	 * </pre>
	 * </p>
	 *
	 * @param nrows The number of rows in the matrix.
	 * @param ncols The number of columns in the matrix.
	 * @param array The array representing the matrix in row-major order.
	 */
	public Matrix(int nrows, int ncols, T[] array) {
		this.nrows = nrows;
		this.ncols = ncols;
		this.array = Arrays.copyOf(array, array.length);
	}

	/**
	 * Constructor to create a matrix from a 2D array by copying its elements.
	 * <p>
	 * This constructor initializes a matrix with the dimensions derived from the
	 * given 2D array. It uses the
	 * {@link System#arraycopy(Object, int, Object, int, int)} method to copy
	 * elements from the input array to the internal array representation of the
	 * matrix, ensuring that the matrix is populated with the correct elements in
	 * row-major order.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Integer[][] array2D = { { 1, 2, 3 }, { 4, 5, 6 } };
	 * Matrix<Integer> matrix = new Matrix<>(array2D);
	 * </pre>
	 * </p>
	 *
	 * @param array2D The base 2D array from which the matrix elements are copied.
	 */
	public Matrix(T[][] array2D) {
		this.nrows = array2D.length;
		this.ncols = array2D[0].length;
		this.array = Arrays.copyOf(array2D[0], array2D[0].length * array2D.length);

		for (int i = 0; i < nrows; i++) {
			System.arraycopy(array2D[i], 0, this.array, i * ncols, ncols);
		}
	}

	/**
	 * Constructor to create a null matrix (initialized to 0) with specified
	 * dimensions.
	 * <p>
	 * This constructor initializes a matrix with the specified number of rows and
	 * columns, and populates it with zeros. It uses a generic approach to create an
	 * array of type T, where T extends Number, to ensure that the matrix can hold
	 * numeric values. The array is initialized to the size of the matrix (rows *
	 * columns) and filled with zeros.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * </pre>
	 * </p>
	 *
	 * @param nrows The number of rows in the matrix.
	 * @param ncols The number of columns in the matrix.
	 */
	@SuppressWarnings("unchecked")
	public Matrix(int nrows, int ncols) {
		this.nrows = nrows;
		this.ncols = ncols;
		this.array = (T[]) Arrays.copyOf(new Number[0], nrows * ncols);
	}

	/**
	 * Constructor. Create a matrix initialized with a constant value.
	 * <p>
	 * This constructor initializes a matrix with the specified number of rows and
	 * columns, and fills it with the provided constant value. It uses the
	 * {@link Arrays#fill(Object[], Object)} method to populate the internal array
	 * with the constant value, ensuring that every element of the matrix is
	 * initialized to the specified value.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3, 1.0);
	 * </pre>
	 * </p>
	 *
	 * @param nrows The number of rows in the matrix.
	 * @param ncols The number of columns in the matrix.
	 * @param value The constant value to initialize the matrix with.
	 */
	public Matrix(int nrows, int ncols, T value) {
		this(nrows, ncols);
		Arrays.fill(array, value);
	}

	/**
	 * Constructor to create a matrix from a 1D array by specifying the number of
	 * rows only.
	 * <p>
	 * This constructor initializes a matrix with the specified number of rows and
	 * calculates the number of columns based on the length of the provided 1D array
	 * and the number of rows. It checks if the total number of elements in the
	 * array matches the expected total number of elements for the matrix (rows *
	 * columns). If the sizes do not match, an {@link IllegalArgumentException} is
	 * thrown.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Integer[] array1D = { 1, 2, 3, 4, 5, 6 };
	 * Matrix<Integer> matrix = new Matrix<>(array1D, 2);
	 * </pre>
	 * </p>
	 *
	 * @param array1D The 1D array of real numbers stored in row-major order.
	 * @param mrows   The number of rows in the matrix.
	 * @throws IllegalArgumentException if the array size is not correct.
	 */
	public Matrix(T[] array1D, int mrows) {
		this.nrows = mrows;
		this.ncols = (mrows != 0 ? array1D.length / mrows : 0);
		if (mrows * ncols != array1D.length) {
			throw new IllegalArgumentException("The size of the array is not correct!");
		}
		this.array = Arrays.copyOf(array1D, array1D.length);
	}

	/**
	 * Constructor to create a matrix from a 1D array by specifying the number of
	 * columns only.
	 * <p>
	 * This constructor initializes a matrix with the specified number of columns
	 * and calculates the number of rows based on the length of the provided 1D
	 * array and the number of columns. It checks if the total number of elements in
	 * the array matches the expected total number of elements for the matrix (rows
	 * * columns). If the sizes do not match, an {@link IllegalArgumentException} is
	 * thrown.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Float[] array1D = { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0 };
	 * Matrix<Float> matrix = new Matrix<>(3, array1D);
	 * </pre>
	 * </p>
	 *
	 * @param mcols   The number of columns in the matrix.
	 * @param array1D The 1D array of real numbers stored in row-major order.
	 * @throws IllegalArgumentException if the array size is not correct.
	 */
	public Matrix(int mcols, T[] array1D) {
		this.ncols = mcols;
		this.nrows = (mcols != 0 ? array1D.length / mcols : 0);
		if (mcols * nrows != array1D.length) {
			throw new IllegalArgumentException("The size of the array is not correct!");
		}
		this.array = Arrays.copyOf(array1D, array1D.length);
	}

	/* GETTERS / SETTERS */

	/**
	 * Retrieves the internal array representing the matrix in 1D format.
	 * <p>
	 * This method returns the internal array used to store the matrix elements. The
	 * array is in a flat, row-major order, meaning that the elements of each row
	 * are stored consecutively in the array.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<Double>(3, 3);
	 * // Assume matrix is initialized with values
	 * Double[] array = matrix.getArray();
	 * // This permits to set elements easily as
	 * array[0] = 1.0;
	 * array[1] = 2.0;
	 * array[2] = 3.0;
	 * array[3] = 4.0;
	 * array[4] = 5.0;
	 * array[5] = 6.0;
	 * array[6] = 7.0;
	 * array[7] = 8.0;
	 * array[8] = 9.0;
	 * </pre>
	 * </p>
	 *
	 * @return The internal array representing the matrix.
	 */
	public T[] getArray() {
		return array;
	}

	/**
	 * Sets the internal array representing the matrix.
	 * <p>
	 * This method updates the internal array used to store the matrix elements. It
	 * takes a new array as input and copies its contents using the
	 * {@link Arrays#copyOf(Object[], int)} method to ensure that the matrix's
	 * internal representation is updated correctly.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Short> matrix = new Matrix<Short>(3, 3);
	 * Short[] newArray = new T[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	 * matrix.setArray(newArray);
	 * </pre>
	 * </p>
	 *
	 * @param array The new array to set as the matrix's internal representation.
	 */
	public void setArray(T[] array) {
		this.array = Arrays.copyOf(array, array.length);
	}

	/**
	 * Retrieves the number of rows in the matrix.
	 * <p>
	 * This method returns the number of rows in the matrix as an integer. It is a
	 * simple getter method that provides access to the matrix's row count.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Float> matrix = new Matrix<Float>(3, 3);
	 * // Assume matrix is initialized with values
	 * int numRows = matrix.getNrows();
	 * System.out.println("Number of rows: " + numRows);
	 * </pre>
	 * </p>
	 *
	 * @return The number of rows in the matrix.
	 */
	public int getNrows() {
		return nrows;
	}

	/**
	 * Sets the number of rows in the matrix.
	 * <p>
	 * This method updates the number of rows in the matrix. If the new number of
	 * rows is different from the current number, it creates a new array with the
	 * updated size and copies the existing elements into it, preserving the
	 * existing matrix data. This operation may require resizing the internal array
	 * to accommodate the new dimensions, ensuring that the matrix's structure
	 * remains consistent.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Float> matrix = new Matrix<Float>(3, 3);
	 * matrix.setNrows(4); // Resizes the matrix to 4 rows
	 * </pre>
	 * </p>
	 *
	 * @param nrows The new number of rows for the matrix.
	 */
	public void setNrows(int nrows) {
		if (nrows != this.nrows) {
			T[] newArray = Arrays.copyOf(array, nrows * ncols);
			this.array = newArray;
			this.nrows = nrows;
		}
	}

	/**
	 * Retrieves the number of columns in the matrix.
	 * <p>
	 * This method returns the number of columns in the matrix as an integer. It is
	 * a simple getter method that provides access to the matrix's column count.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<Double>(3, 3);
	 * int numCols = matrix.getNcols();
	 * System.out.println("Number of columns: " + numCols);
	 * </pre>
	 * </p>
	 *
	 * @return The number of columns in the matrix.
	 */
	public int getNcols() {
		return ncols;
	}

	/**
	 * Sets the number of columns in the matrix.
	 * <p>
	 * This method updates the number of columns in the matrix. If the new number of
	 * columns is different from the current number, it creates a new array with the
	 * updated size and copies the existing elements into it, preserving the
	 * existing matrix data. This operation may require resizing the internal array
	 * to accommodate the new dimensions, ensuring that the matrix's structure
	 * remains consistent.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Long> matrix = new Matrix<>(3, 3);
	 * matrix.setNcols(4); // Resizes the matrix to 4 columns
	 * </pre>
	 * </p>
	 *
	 * @param ncols The new number of columns for the matrix.
	 */
	public void setNcols(int ncols) {
		if (ncols != this.ncols) {
			T[] newArray = Arrays.copyOf(array, nrows * ncols);
			this.array = newArray;
			this.ncols = ncols;
		}
	}

	/**
	 * Retrieves the value of a specific element in the matrix.
	 * <p>
	 * This method returns the value of the element at the specified row and column
	 * indices. The indices are zero-based, meaning that the first element is at row
	 * 0, column 0.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Long> matrix = new Matrix<Long>(2, 2);
	 * // Fill the matrix with values
	 * matrix.getArray()[0] = 1;
	 * matrix.getArray()[1] = 2;
	 * matrix.getArray()[2] = 3;
	 * matrix.getArray()[3] = 4;
	 * // Assume matrix is initialized with values
	 * Long value = matrix.get(0, 1); // Retrieves the value at row 0, column 1
	 * </pre>
	 * </p>
	 *
	 * @param row The row index of the element to retrieve.
	 * @param col The column index of the element to retrieve.
	 * @return The value of the element at the specified row and column indices.
	 */
	public T get(int row, int col) {
		return array[row * ncols + col];
	}

	/**
	 * Sets the value of a specific element in the matrix.
	 * <p>
	 * This method updates the value of the element at the specified row and column
	 * indices. The indices are zero-based, meaning that the first element is at row
	 * 0, column 0.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Float> matrix = new Matrix<Float>(3, 3);
	 * matrix.set(1, 2, 5.0); // Sets the value at row 1, column 2 to 5.0
	 * </pre>
	 * </p>
	 *
	 * @param row   The row index of the element to update.
	 * @param col   The column index of the element to update.
	 * @param value The new value to set for the specified element.
	 */
	public void set(int row, int col, T value) {
		array[row * ncols + col] = value;
	}

	/**
	 * Retrieves the value of a specific element in the matrix's internal 1D array.
	 * <p>
	 * This method returns the value of the element at the specified index in the
	 * internal 1D array representing the matrix. The index is zero-based, and it
	 * directly corresponds to the row-major order of the matrix's elements.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Integer> matrix = new Matrix<Integer>(3, 3);
	 * // Assume matrix is initialized with values
	 * Integer value = matrix.get(2); // Retrieves the value at index 2 in the internal array
	 * </pre>
	 * </p>
	 *
	 * @param i The index in the internal 1D array of the element to retrieve.
	 * @return The value of the element at the specified index.
	 */
	public T get(int i) {
		return array[i];
	}

	/**
	 * Sets the value of a specific element in the matrix's internal 1D array.
	 * <p>
	 * This method updates the value of the element at the specified index in the
	 * internal 1D array representing the matrix. The index is zero-based, and it
	 * directly corresponds to the row-major order of the matrix's elements.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * matrix.set(2, 10.0); // Sets the value at index 2 in the internal array to 10.0
	 * </pre>
	 * </p>
	 *
	 * @param i     The index in the internal 1D array of the element to update.
	 * @param value The new value to set for the specified element.
	 */
	public void set(int i, T value) {
		array[i] = value;
	}

	/**
	 * Sets a range of elements in the matrix to the values in a given 2D array.
	 * <p>
	 * 
	 * 
	 * This method updates a range of elements in the matrix, starting from the
	 * specified starting index and ending before the specified ending index, with
	 * the values from the provided 2D array. The method checks if the indices and
	 * array size are valid to ensure that the matrix's structure remains
	 * consistent. If the indices are not valid or the array size does not match the
	 * expected range, an {@link IllegalArgumentException} is thrown.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Integer> matrix = new Matrix<>(3, 3);
	 * Integer[][] newValues = { { 7, 8, 9 }, { 10, 11, 12 } };
	 * matrix.set(0, 6, newValues); // Sets the values from index 0 to index 5
	 * </pre>
	 * </p>
	 *
	 * @param from   The starting index (inclusive) for setting new values.
	 * @param to     The ending index (exclusive) for setting new values.
	 * @param arrayB The 2D array containing the new values.
	 * @return The current matrix after setting the new values.
	 * @throws IllegalArgumentException if the indices are not valid or the array
	 *                                  size is not correct.
	 */
	public Matrix<T> set(int from, int to, T[][] arrayB) {
		if (from < 0 || to > this.nrows * this.ncols || from >= to
				|| (to - from) != (arrayB.length * arrayB[0].length)) {
			throw new IllegalArgumentException("Invalid indices or array size!");
		}

		int index = from;
		for (T[] row : arrayB) {
			for (T value : row) {
				this.array[index++] = value;
			}
		}

		return this;
	}

	/**
	 * Sets a range of elements in the matrix to the values in a given matrix
	 * object.
	 * <p>
	 * This method updates a range of elements in the matrix, starting from the
	 * specified starting index and ending before the specified ending index, with
	 * the values from another matrix object. The method checks if the indices and
	 * the size of the matrix object are valid to ensure that the matrix's structure
	 * remains consistent. If the indices are not valid or the matrix object's size
	 * does not match the expected range, an {@link IllegalArgumentException} is
	 * thrown.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrixA = new Matrix<>(3, 3);
	 * // Assume matrixA is initialized with values
	 * Matrix<Double> matrixB = new Matrix<>(2, 2);
	 * // Assume matrixB is initialized with different values
	 * matrixA.set(0, 4, matrixB); // Sets the values from matrixB into matrixA starting at index 0
	 * </pre>
	 * </p>
	 *
	 * @param from The starting index (inclusive) for setting new values.
	 * @param to   The ending index (exclusive) for setting new values.
	 * @param B    The matrix object containing the new values.
	 * @return The current matrix after setting the new values.
	 * @throws IllegalArgumentException if the indices are not valid or the array
	 *                                  size is not correct.
	 */
	public Matrix<T> set(int from, int to, Matrix<T> B) {
		if (from < 0 || to > this.nrows * this.ncols || from >= to || (to - from) != (B.nrows * B.ncols)) {
			throw new IllegalArgumentException("Invalid indices or array size!");
		}

		System.arraycopy(B.array, 0, this.array, from, B.array.length);

		return this;
	}

	/**
	 * Sets values in a submatrix of the current matrix, specified by start and end
	 * indices for both rows and columns.
	 * <p>
	 * This method updates a submatrix of the current matrix with the values
	 * provided in a 2D array. The submatrix is defined by the start and end indices
	 * for both rows and columns. The method checks if the indices are valid and if
	 * the dimensions of the values array match the size of the submatrix. If the
	 * indices are invalid or the dimensions do not match, an
	 * {@link IllegalArgumentException} is thrown.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with values
	 * Double[][] newValues = { { 7.0, 8.0 }, { 9.0, 10.0 } };
	 * matrix.set(1, 2, 3, 4, newValues); // Sets the values from newValues into the submatrix of matrix
	 * </pre>
	 * </p>
	 *
	 * @param rowStart The start index for the row (inclusive).
	 * @param rowEnd   The end index for the row (exclusive).
	 * @param colStart The start index for the column (inclusive).
	 * @param colEnd   The end index for the column (exclusive).
	 * @param values   A 2D array of values to set in the submatrix. The array must
	 *                 have dimensions equal to the size of the submatrix.
	 * @throws IllegalArgumentException If the indices are invalid or the dimensions
	 *                                  of values do not match the submatrix size.
	 */
	public void set(int rowStart, int rowEnd, int colStart, int colEnd, T[][] values) {
		if (rowStart < 1 || rowEnd > nrows || colStart < 1 || colEnd > ncols || rowStart > rowEnd
				|| colStart > colEnd) {
			throw new IllegalArgumentException("Invalid indices for submatrix!");
		}
		if (values.length != rowEnd - rowStart + 1 || values[0].length != colEnd - colStart + 1) {
			throw new IllegalArgumentException("The dimensions of values do not match the submatrix size.");
		}

		for (int i = rowStart; i <= rowEnd; i++) {
			for (int j = colStart; j <= colEnd; j++) {
				set((i - 1), (j - 1), values[i - rowStart][j - colStart]);
			}
		}
	}

	/**
	 * Sets values in a submatrix of the current matrix, specified by start and end
	 * indices for both rows and columns.
	 * <p>
	 * This method updates a submatrix of the current matrix with the values
	 * provided in another matrix object. The submatrix is defined by the start and
	 * end indices for both rows and columns. The method checks if the indices are
	 * valid and if the dimensions of the values matrix match the size of the
	 * submatrix. If the indices are invalid or the dimensions do not match, an
	 * {@link IllegalArgumentException} is thrown.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with values
	 * Matrix<Double> subMatrix = new Matrix<>(2, 2);
	 * // Assume subMatrix is initialized with different values
	 * matrix.set(1, 2, 3, 4, subMatrix); // Sets the values from subMatrix into the submatrix of matrix
	 * </pre>
	 * </p>
	 *
	 * @param rowStart The start index for the row (inclusive).
	 * @param rowEnd   The end index for the row (exclusive).
	 * @param colStart The start index for the column (inclusive).
	 * @param colEnd   The end index for the column (exclusive).
	 * @param q        A matrix object of values to set in the submatrix. The array
	 *                 must have dimensions equal to the size of the submatrix.
	 * @throws IllegalArgumentException If the indices are invalid or the dimensions
	 *                                  of values do not match the submatrix size.
	 */
	public void set(int rowStart, int rowEnd, int colStart, int colEnd, Matrix<T> q) {
		T[][] values = q.to2DArray();
		if (rowStart < 1 || rowEnd > nrows || colStart < 1 || colEnd > ncols || rowStart > rowEnd
				|| colStart > colEnd) {
			throw new IllegalArgumentException("Invalid indices for submatrix!");
		}
		if (values.length != rowEnd - rowStart + 1 || values[0].length != colEnd - colStart + 1) {
			throw new IllegalArgumentException("The dimensions of values do not match the submatrix size.");
		}

		for (int i = rowStart; i <= rowEnd; i++) {
			for (int j = colStart; j <= colEnd; j++) {
				set((i - 1), (j - 1), values[i - rowStart][j - colStart]);
			}
		}
	}

	/**
	 * Generates a matrix filled with random values.
	 * <p>
	 * This method creates a new matrix of the specified dimensions and fills it
	 * with random values. The type of the values is determined by the generic type
	 * parameter T, which should extend Number to accommodate various numeric types.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = Matrix.generateRandomMatrix(3, 3);
	 * // matrix is now filled with random Double values
	 * </pre>
	 * </p>
	 *
	 * @param nrows Number of rows for the new matrix.
	 * @param ncols Number of columns for the new matrix.
	 * @return A new matrix filled with random values.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T> generateRandomMatrix(int nrows, int ncols) {
		Matrix<T> matrix = new Matrix<>(nrows, ncols);
		Random random = new Random();
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				// Assuming T is a Double for simplicity.
				// You may need to adjust this part based on the actual type of T.
				matrix.set(i, j, (T) Double.valueOf(random.nextDouble()));
			}
		}
		return matrix;
	}

	/**
	 * Generates a binary matrix filled with 0s and 1s.
	 * <p>
	 * This method creates a new matrix of the specified dimensions and fills it
	 * with binary values (0s and 1s) generated randomly. The type of the values is
	 * determined by the generic type parameter T, which is expected to be an
	 * Integer or a type that can be cast from an Integer. This method is
	 * particularly useful for generating matrices for binary operations or matrices
	 * with binary values, such as adjacency matrices or matrices used in boolean
	 * logic operations.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Integer> binaryMatrix = generateBinaryMatrix(3, 3);
	 * // binaryMatrix is now filled with random 0s and 1s
	 * </pre>
	 * </p>
	 *
	 * @param nrows Number of rows for the new binary matrix.
	 * @param ncols Number of columns for the new binary matrix.
	 * @return A new matrix filled with binary values (0s and 1s).
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T> generateBinaryMatrix(int nrows, int ncols) {
		Matrix<T> matrix = new Matrix<>(nrows, ncols);
		Random random = new Random();
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				// Assuming T is an Integer for simplicity.
				// You may need to adjust this part based on the actual type of T.
				matrix.set(i, j, (T) Integer.valueOf(random.nextInt(2)));
			}
		}
		return matrix;
	}

	/*****************************************
	 * ARRAYS MANIPULATION METHODS
	 ***************************************/

	/**
	 * Converts the internal 1D array to a 2D array.
	 * <p>
	 * This method transforms the internal 1D array representation of the matrix
	 * into a 2D array, where each row of the 2D array corresponds to a row in the
	 * matrix. This conversion is useful for operations that require a 2D view of
	 * the matrix data, such as iterating over rows and columns directly.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<Double>(3, 3);
	 * // Assume matrix is initialized with values
	 * T[][] matrix2D = matrix.to2DArray();
	 * // matrix2D now represents the matrix in a 2D format
	 * </pre>
	 * </p>
	 *
	 * @return The internal array as a 2D array.
	 */
	@SuppressWarnings("unchecked")
	public T[][] to2DArray() {
		T[] array1d = this.getArray();
		T[][] array2d = (T[][]) new Number[nrows][ncols];

		for (int i = 0; i < nrows; i++) {
			System.arraycopy(array1d, i * ncols, array2d[i], 0, ncols);
		}
		return array2d;
	}

	/**
	 * Converts the internal 2D array to a 1D array.
	 * <p>
	 * This method transforms the internal 2D array representation of the matrix
	 * into a 1D array, effectively flattening the matrix structure. This conversion
	 * is useful for operations that require a linear view of the matrix data, such
	 * as certain mathematical operations or when interfacing with APIs that expect
	 * 1D arrays.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Float> matrix = new Matrix<Float>(3, 3);
	 * // Assume matrix is initialized with values
	 * T[] matrix1D = matrix.to1DArray();
	 * // matrix1D now represents the matrix in a 1D format
	 * </pre>
	 * </p>
	 *
	 * @return The internal array as a 1D array.
	 */
	public T[] to1DArray() {
		return Arrays.copyOf(this.getArray(), nrows * ncols);
	}

	/**
	 * Clones (or makes a copy of) the current matrix.
	 * <p>
	 * This method creates a new matrix that is a deep copy of the current matrix. A
	 * deep copy means that all elements of the matrix are copied, and the new
	 * matrix is independent of the original matrix. This is useful for scenarios
	 * where you need to modify the matrix without affecting the original data.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Integer> matrix = new Matrix<Integer>(3, 3);
	 * // Assume matrix is initialized with values
	 * Matrix matrixCopy = matrix.clone();
	 * // matrixCopy is now a separate copy of the original matrix
	 * </pre>
	 * </p>
	 *
	 * @return A new matrix that is a clone of the current matrix.
	 */
	public Matrix<T> clone() {
		// Convert the current matrix to a 1D array
		T[] array1D = this.to1DArray();

		// Create a new matrix with the same dimensions and array
		return new Matrix<>(this.nrows, this.ncols, array1D);
	}

	/**
	 * Extracts a range of rows from the matrix.
	 * <p>
	 * This method creates a new matrix that contains a specified range of rows from
	 * the original matrix. The range is defined by the starting and ending indices,
	 * where the starting index is inclusive and the ending index is exclusive. If
	 * the indices are not valid, an {@link IllegalArgumentException} is thrown.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double> extractedRows = matrix.extractRowsRange(1, 3);
	 * // extractedRows now contains the rows from index 1 to index 2 of the
	 * // original matrix
	 * </pre>
	 * </p>
	 *
	 * @param from The starting index (inclusive) of the row range to extract.
	 * @param to   The ending index (exclusive) of the row range to extract.
	 * @return A new matrix that contains the specified range of rows.
	 * @throws IllegalArgumentException if the indices are not valid.
	 */
	public Matrix<T> extractRowsRange(int from, int to) {
		if (from < 0 || to > this.nrows || from >= to) {
			throw new IllegalArgumentException("Invalid row indices!");
		}

		int newNrows = to - from;
		T[] newArray = Arrays.copyOfRange(this.array, from * this.ncols, to * this.ncols);

		return new Matrix<>(newNrows, this.ncols, newArray);
	}

	/**
	 * Extracts a range of columns from the matrix.
	 * <p>
	 * This method creates a new matrix that contains a specified range of columns
	 * from the original matrix. The range is defined by the starting and ending
	 * indices, where the starting index is inclusive and the ending index is
	 * exclusive. If the indices are not valid, an {@link IllegalArgumentException}
	 * is thrown.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double> extractedColumns = matrix.extractColumnsRange(1, 3);
	 * // extractedColumns now contains the columns from index 1 to index 2 of
	 * // the original matrix
	 * </pre>
	 * </p>
	 *
	 * @param from The starting index (inclusive) of the column range to extract.
	 * @param to   The ending index (exclusive) of the column range to extract.
	 * @return A new matrix that contains the specified range of columns.
	 * @throws IllegalArgumentException if the indices are not valid.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T> extractColumnsRange(int from, int to) {
		if (from < 0 || to > this.ncols || from >= to) {
			throw new IllegalArgumentException("Invalid column indices!");
		}

		int newNcols = to - from;
		T[] newArray = (T[]) new Number[newNcols * this.nrows];

		for (int i = 0; i < this.nrows; i++) {
			for (int j = from; j < to; j++) {
				newArray[i * newNcols + (j - from)] = this.array[i * this.ncols + j];
			}
		}

		return new Matrix<>(this.nrows, newNcols, newArray);
	}

	/**
	 * Extracts a specific row from the matrix.
	 * <p>
	 * This method creates a new matrix that contains a single row from the original
	 * matrix. The row is specified by its index. If the row index is not valid, an
	 * {@link IllegalArgumentException} is thrown.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double> extractedRow = matrix.extractRow(1);
	 * // extractedRow now contains the row at index 1 of the original matrix
	 * </pre>
	 * </p>
	 *
	 * @param i The index of the row to extract.
	 * @return A new matrix that is a single row of the original matrix.
	 * @throws IllegalArgumentException if the row index is not valid.
	 */
	public Matrix<T> extractRow(int i) {
		if (i < 0 || i >= nrows) {
			throw new IllegalArgumentException("Invalid row index.");
		}

		T[] newRow = Arrays.copyOfRange(this.array, i * ncols, (i + 1) * ncols);

		return new Matrix<>(1, ncols, newRow);
	}

	/**
	 * Returns the specified row of the matrix as a new matrix.
	 * <p>
	 * This method extracts a single row from the original matrix and returns it as
	 * a new matrix. The row is specified by its index. If the row index is not
	 * valid, an {@link IllegalArgumentException} is thrown.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double> rowMatrix = matrix.getRow(1);
	 * // rowMatrix now contains the row at index 1 of the original matrix
	 * </pre>
	 * </p>
	 *
	 * @param row The index of the row to return.
	 * @return The specified row as a new matrix.
	 * @see extractRow(int i)
	 */
	public Matrix<T> getRow(int row) {
		Matrix<T> rowMatrix = new Matrix<>(1, ncols);
		for (int i = 0; i < ncols; i++) {
			rowMatrix.set(0, i, get(row, i));
		}
		return rowMatrix;
	}

	/**
	 * Sets the specified row of the matrix to the given row.
	 * <p>
	 * This method replaces a row in the matrix with a new row provided as a matrix.
	 * The row to be replaced is specified by its index, and the new row must be a
	 * matrix with the same number of columns as the original matrix. If the new row
	 * matrix does not have the correct dimensions, an
	 * {@link IllegalArgumentException} is thrown.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double> newRowMatrix = new Matrix<>(1, 3);
	 * // Initialize newRowMatrix with new values
	 * matrix.setRow(1, newRowMatrix);
	 * // The row at index 1 of matrix is now replaced with newRowMatrix
	 * </pre>
	 * </p>
	 *
	 * @param row The index of the row to set.
	 * @param X   The new row matrix.
	 * @throws IllegalArgumentException if the new row matrix dimensions are not
	 *                                  valid.
	 */
	public void setRow(int row, Matrix<T> X) {
		if (X.getNrows() != 1 || X.getNcols() != ncols) {
			throw new IllegalArgumentException("Invalid row matrix dimensions.");
		}
		for (int i = 0; i < ncols; i++) {
			set(row, i, X.get(0, i));
		}
	}

	/**
	 * Extracts a specific column from the matrix.
	 * <p>
	 * This method creates a new matrix that contains a single column from the
	 * original matrix. The column is specified by its index. If the column index is
	 * not valid, an {@link IllegalArgumentException} is thrown.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double> columnMatrix = matrix.extractCol(1);
	 * // columnMatrix now contains the column at index 1 of the original matrix
	 * </pre>
	 * </p>
	 *
	 * @param i The index of the column to extract.
	 * @return A new matrix that is a single column of the original matrix.
	 * @throws IllegalArgumentException if the column index is not valid.
	 * @see getColumn(int col)
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T> extractCol(int i) {
		if (i < 0 || i >= ncols) {
			throw new IllegalArgumentException("Invalid column index.");
		}

		T[] newCol = (T[]) new Number[nrows];

		for (int j = 0; j < nrows; j++) {
			newCol[j] = this.array[j * ncols + i];
		}

		return new Matrix<>(nrows, 1, newCol);
	}

	/**
	 * Returns the specified column of the matrix as a new matrix.
	 * <p>
	 * This method extracts a single column from the original matrix and returns it
	 * as a new matrix. The column is specified by its index. If the column index is
	 * not valid, an {@link IllegalArgumentException} is thrown.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double> columnMatrix = matrix.getColumn(1);
	 * // columnMatrix now contains the column at index 1 of the original matrix
	 * </pre>
	 * </p>
	 *
	 * @param col The index of the column to return.
	 * @return The specified column as a new matrix.
	 * @see extractCol(int i)
	 */
	public Matrix<T> getColumn(int col) {
		Matrix<T> column = new Matrix<>(nrows, 1);
		for (int i = 0; i < nrows; i++) {
			column.set(i, 0, get(i, col));
		}
		return column;
	}

	/**
	 * Sets the specified column of the matrix to the given column.
	 * <p>
	 * This method replaces a column in the matrix with a new column provided as a
	 * matrix. The column to be replaced is specified by its index, and the new
	 * column must be a matrix with the same number of rows as the original matrix.
	 * If the new column matrix does not have the correct dimensions, an
	 * {@link IllegalArgumentException} is thrown.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double> newColumnMatrix = new Matrix<>(3, 1);
	 * // Initialize newColumnMatrix with new values
	 * matrix.setColumn(1, newColumnMatrix);
	 * // The column at index 1 of matrix is now replaced with newColumnMatrix
	 * </pre>
	 * </p>
	 *
	 * @param col The index of the column to set.
	 * @param X   The new column matrix.
	 * @throws IllegalArgumentException if the new column matrix dimensions are not
	 *                                  valid.
	 */
	public void setColumn(int col, Matrix<T> X) {
		if (X.getNrows() != nrows || X.getNcols() != 1) {
			throw new IllegalArgumentException("Invalid column Matrix dimensions.");
		}
		for (int i = 0; i < nrows; i++) {
			set(i, col, X.get(i, 0));
		}
	}

	/**
	 * Extracts a subMatrix from the matrix.
	 * <p>
	 * This method creates a new matrix that contains a submatrix of the original
	 * matrix. The submatrix is defined by the starting and ending indices for both
	 * rows and columns. If the indices are not valid, an
	 * {@link IllegalArgumentException} is thrown.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double> subMatrix = matrix.extract(1, 3, 1, 3);
	 * // subMatrix now contains the submatrix from row 1 to row 2 and column 1
	 * // to column 2 of the original matrix
	 * </pre>
	 * </p>
	 *
	 * @param rowstart The starting row index (inclusive) of the subMatrix to
	 *                 extract.
	 * @param rowend   The ending row index (exclusive) of the subMatrix to extract.
	 * @param colstart The starting column index (inclusive) of the subMatrix to
	 *                 extract.
	 * @param colend   The ending column index (exclusive) of the subMatrix to
	 *                 extract.
	 * @return A new matrix that is a subMatrix of the original matrix.
	 * @throws IllegalArgumentException if the indices are not valid.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T> extract(int rowstart, int rowend, int colstart, int colend) {
		if (rowstart < 0 || rowend > this.nrows || rowstart >= rowend || colstart < 0 || colend > this.ncols
				|| colstart >= colend) {
			throw new IllegalArgumentException("Invalid row or column indices!");
		}

		int newNrows = rowend - rowstart;
		int newNcols = colend - colstart;
		T[] newArray = (T[]) new Number[newNrows * newNcols];

		for (int i = rowstart; i < rowend; i++) {
			for (int j = colstart; j < colend; j++) {
				newArray[(i - rowstart) * newNcols + (j - colstart)] = this.array[i * this.ncols + j];
			}
		}

		return new Matrix<>(newNrows, newNcols, newArray);
	}

	/**
	 * Transforms the matrix into a row vector.
	 * <p>
	 * This method converts the matrix into a row vector by creating a new matrix
	 * with a single row and the same number of columns as the original matrix. This
	 * operation is useful for flattening the matrix into a single row, which can be
	 * useful for certain matrix operations or when interfacing with APIs that
	 * expect a single row vector.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double> rowVector = matrix.transformToRowVector();
	 * // rowVector now represents the matrix as a single row vector
	 * </pre>
	 * </p>
	 *
	 * @return The matrix transformed into a row vector.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T> transformToRowVector() {
		// Cast the array to the generic type
		T[] x = (T[]) getArray();
		// Create a new 2D array with one row
		T[][] C = (T[][]) new Number[1][x.length];
		// Copy the elements from the 1D array to the 2D array
		System.arraycopy(x, 0, C[0], 0, x.length);
		// Return the new Matrix
		return new Matrix<>(C);
	}

	/**
	 * Transforms the matrix into a column vector.
	 * <p>
	 * This method converts the matrix into a column vector by creating a new matrix
	 * with a single column and the same number of rows as the original matrix. This
	 * operation is useful for flattening the matrix into a single column, which can
	 * be useful for certain matrix operations or when interfacing with APIs that
	 * expect a single column vector.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double> columnVector = matrix.transformToColumnVector();
	 * // columnVector now represents the matrix as a single column vector
	 * </pre>
	 * </p>
	 *
	 * @return The matrix transformed into a column vector.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T> transformToColumnVector() {
		// Cast the array to the generic type
		T[] x = (T[]) getArray();
		// Create a new 2D array with one column
		T[][] C = (T[][]) new Number[x.length][1];
		// Copy the elements from the 1D array to the 2D array
		for (int i = 0; i < x.length; i++) {
			C[i][0] = x[i];
		}
		// Return the new Matrix
		return new Matrix<>(C);
	}

	/**
	 * Concatenates multiple matrices horizontally.
	 * <p>
	 * This method concatenates the current matrix with one or more additional
	 * matrices horizontally. The operation requires that all matrices have the same
	 * number of rows. The resulting matrix will have the same number of rows as the
	 * original matrix but its number of columns will be the sum of the columns of
	 * all matrices.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix1 = new Matrix<>(3, 3);
	 * // Assume matrix1 is initialized with Double values
	 * Matrix<Double> matrix2 = new Matrix<>(3, 2);
	 * // Assume matrix2 is initialized with Double values
	 * Matrix<Double> concatenated = matrix1.concatenateHorizontally(matrix2);
	 * // concatenated now contains the concatenation of matrix1 and matrix2
	 * // horizontally
	 * </pre>
	 * </p>
	 *
	 * @param matrices Varargs parameter representing the matrices to concatenate.
	 * @return The concatenated matrix.
	 * @throws IllegalArgumentException if the matrices do not have the same number
	 *                                  of rows.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T> concatenateHorizontally(Matrix<T>... matrices) {
		// Calculate the total number of columns in the resulting Matrix
		int totalCols = this.ncols;
		for (Matrix<T> Matrix : matrices) {
			if (Matrix.nrows != this.nrows) {
				throw new IllegalArgumentException("All matrices must have the same number of rows!");
			}
			totalCols += Matrix.ncols;
		}

		// Create a new array to hold the elements of the concatenated Matrix
		T[] newArray = Arrays.copyOf(this.array, this.nrows * totalCols);

		// Copy the elements from the input matrices into the new array
		int colOffset = this.ncols;
		for (Matrix<T> Matrix : matrices) {
			for (int i = 0; i < Matrix.nrows; i++) {
				System.arraycopy(Matrix.array, i * Matrix.ncols, newArray, i * totalCols + colOffset, Matrix.ncols);
			}
			colOffset += Matrix.ncols;
		}

		// Create and return a new Matrix object with the new array
		return new Matrix<>(this.nrows, totalCols, newArray);
	}

	/**
	 * Concatenates multiple matrices vertically.
	 * <p>
	 * This method concatenates the current matrix with one or more additional
	 * matrices vertically. The operation requires that all matrices have the same
	 * number of columns. The resulting matrix will have the same number of columns
	 * as the original matrix but its number of rows will be the sum of the rows of
	 * all matrices.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix1 = new Matrix<>(3, 3);
	 * // Assume matrix1 is initialized with Double values
	 * Matrix<Double> matrix2 = new Matrix<>(2, 3);
	 * // Assume matrix2 is initialized with Double values
	 * Matrix<Double> concatenated = matrix1.concatenateVertically(matrix2);
	 * // concatenated now contains the concatenation of matrix1 and matrix2
	 * // vertically
	 * </pre>
	 * </p>
	 *
	 * @param matrices Varargs parameter representing the matrices to concatenate.
	 * @return The concatenated matrix.
	 * @throws IllegalArgumentException if the matrices do not have the same number
	 *                                  of columns.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T> concatenateVertically(Matrix<T>... matrices) {
		// Calculate the total number of rows in the resulting Matrix
		int totalRows = this.nrows;
		for (Matrix<T> Matrix : matrices) {
			if (Matrix.ncols != this.ncols) {
				throw new IllegalArgumentException("All matrices must have the same number of columns!");
			}
			totalRows += Matrix.nrows;
		}

		// Create a new array to hold the elements of the concatenated Matrix
		T[] newArray = Arrays.copyOf(this.array, totalRows * this.ncols);

		// Copy the elements from the input matrices into the new array
		int rowOffset = this.nrows;
		for (Matrix<T> Matrix : matrices) {
			System.arraycopy(Matrix.array, 0, newArray, rowOffset * this.ncols, Matrix.nrows * Matrix.ncols);
			rowOffset += Matrix.nrows;
		}

		// Create and return a new Matrix object with the new array
		return new Matrix<>(totalRows, this.ncols, newArray);
	}

	/**
	 * Inserts rows before a specified index in the matrix.
	 * <p>
	 * This method inserts one or more rows before a specified index in the matrix.
	 * The rows to be inserted are provided as a varargs parameter of arrays, where
	 * each array represents a row. If the index is not valid, an
	 * {@link IllegalArgumentException} is thrown.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Double[][] rowsToInsert = { { 1.0, 2.0, 3.0 }, { 4.0, 5.0, 6.0 } };
	 * Matrix<Double> updatedMatrix = matrix.insertRowsBefore(1, rowsToInsert);
	 * // updatedMatrix now contains the original matrix with two new rows
	 * // inserted before the second row
	 * </pre>
	 * </p>
	 *
	 * @param indexBefore The index before which to insert the rows.
	 * @param y           The rows to insert, provided as varargs of arrays.
	 * @return The current matrix after inserting the rows.
	 * @throws IllegalArgumentException if the index is not valid.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T> insertRowsBefore(int indexBefore, T[]... y) {
		if (indexBefore < 0 || indexBefore > nrows) {
			throw new IllegalArgumentException("Invalid row index.");
		}

		Matrix<T> Y = new Matrix<>(y);
		Matrix<T> top = this.extractRowsRange(0, indexBefore);
		Matrix<T> bottom = this.extractRowsRange(indexBefore, nrows);

		return top.concatenateVertically(Y, bottom);
	}

	/**
	 * Inserts columns before a specified index in the matrix.
	 * <p>
	 * This method inserts one or more columns before a specified index in the
	 * matrix. The columns to be inserted are provided as a varargs parameter of
	 * arrays, where each array represents a column. If the index is not valid, an
	 * {@link IllegalArgumentException} is thrown.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Double[][] columnsToInsert = { { 1.0 }, { 2.0 }, { 3.0 } };
	 * Matrix<Double> updatedMatrix = matrix.insertColumnsBefore(1, columnsToInsert);
	 * // updatedMatrix now contains the original matrix with three new columns
	 * // inserted before the second column
	 * </pre>
	 * </p>
	 *
	 * @param indexBefore The index before which to insert the columns.
	 * @param y           The columns to insert, provided as varargs of arrays.
	 * @return The current matrix after inserting the columns.
	 * @throws IllegalArgumentException if the index is not valid.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T> insertColumnsBefore(int indexBefore, T[]... y) {
		if (indexBefore < 0 || indexBefore > ncols) {
			throw new IllegalArgumentException("Invalid column index.");
		}

		Matrix<T> Y = new Matrix<>(y).transpose();
		Matrix<T> left = this.extractColumnsRange(0, indexBefore);
		Matrix<T> right = this.extractColumnsRange(indexBefore, ncols);

		return left.concatenateVertically(Y, right);
	}

	/**
	 * Inserts values before a specified index in the matrix.
	 * <p>
	 * This method inserts one or more values before a specified index in the
	 * matrix. The values to be inserted are provided as varargs, where each value
	 * represents an element of the matrix. If the index is not valid, an
	 * {@link IllegalArgumentException} is thrown.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Double[] valuesToInsert = { 1.0, 2.0, 3.0 };
	 * Matrix<Double> updatedMatrix = matrix.insertValuesBefore(1, valuesToInsert);
	 * // updatedMatrix now contains the original matrix with three new values
	 * // inserted before the second element
	 * </pre>
	 * </p>
	 *
	 * @param indexBefore The index before which to insert the values.
	 * @param y           The values to insert, provided as varargs.
	 * @return The current matrix after inserting the values.
	 * @throws IllegalArgumentException if the index is not valid.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T> insertValuesBefore(int indexBefore, T... y) {
		if (indexBefore < 0 || indexBefore > nrows * ncols) {
			throw new IllegalArgumentException("Invalid index.");
		}

		T[] newArray = Arrays.copyOf(this.array, this.array.length + y.length);
		System.arraycopy(this.array, indexBefore, newArray, indexBefore + y.length, this.array.length - indexBefore);
		System.arraycopy(y, 0, newArray, indexBefore, y.length);

		return new Matrix<>(nrows, ncols + y.length / nrows, newArray);
	}

	/**
	 * Inserts rows before a specified index in the matrix.
	 * <p>
	 * This method inserts one or more rows before a specified index in the matrix.
	 * The rows to be inserted are provided as varargs of arrays, where each array
	 * represents a row. If the index is not valid, an
	 * {@link IllegalArgumentException} is thrown.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Double[][][] rowsToInsert = { { new Double[] { 1.0, 2.0, 3.0 } }, { new Double[] { 4.0, 5.0, 6.0 } } };
	 * Matrix<Double> updatedMatrix = matrix.insertRowsBefore(1, rowsToInsert);
	 * // updatedMatrix now contains the original matrix with two new rows
	 * // inserted before the second row
	 * </pre>
	 * </p>
	 *
	 * @param indexBefore The index before which to insert the rows.
	 * @param y           The rows to insert, provided as varargs of arrays.
	 * @return The current matrix after inserting the rows.
	 * @throws IllegalArgumentException if the index is not valid.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T> insertRowsBefore(int indexBefore, T[][]... y) {

		T[][] A = to2DArray();
		int totalRows = A.length;
		for (T[][] array : y) {
			totalRows += array.length;
		}

		T[][] result = Arrays.copyOf(A, totalRows);

		int currentIndex = indexBefore;
		for (T[][] array : y) {
			System.arraycopy(array, 0, result, currentIndex, array.length);
			currentIndex += array.length;
		}

		if (indexBefore < A.length) {
			System.arraycopy(A, indexBefore, result, currentIndex, A.length - indexBefore);
		}

		return new Matrix<>(result);
	}

	/**
	 * Inserts rows after a specified index in the matrix.
	 * <p>
	 * This method inserts one or more rows after a specified index in the matrix.
	 * The rows to be inserted are provided as varargs of arrays, where each array
	 * represents a row. If the index is not valid, an
	 * {@link IllegalArgumentException} is thrown.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Double[][][] rowsToInsert = { { new Double[] { 1.0, 2.0, 3.0 } }, { new Double[] { 4.0, 5.0, 6.0 } } };
	 * Matrix<Double> updatedMatrix = matrix.insertAfter(1, rowsToInsert);
	 * // updatedMatrix now contains the original matrix with two new rows
	 * // inserted after the second row
	 * </pre>
	 * </p>
	 *
	 * @param indexAfter The index after which to insert the rows.
	 * @param y          The rows to insert, provided as varargs of arrays.
	 * @return The current matrix after inserting the rows.
	 * @throws IllegalArgumentException if the index is not valid.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T> insertAfter(int indexAfter, T[][]... y) {

		T[][] A = to2DArray();
		int totalRows = A.length;
		for (T[][] array : y) {
			totalRows += array.length;
		}

		T[][] result = Arrays.copyOf(A, totalRows);

		int currentIndex = indexAfter + 1;
		for (T[][] array : y) {
			System.arraycopy(array, 0, result, currentIndex, array.length);
			currentIndex += array.length;
		}

		if (currentIndex < A.length) {
			System.arraycopy(A, currentIndex, result, currentIndex + y.length, A.length - currentIndex);
		}

		return new Matrix<>(result);
	}

	/**
	 * Inserts columns before a specified index in the matrix.
	 * <p>
	 * This method inserts one or more columns before a specified index in the
	 * matrix. The columns to be inserted are provided as varargs of arrays, where
	 * each array represents a column. If the index is not valid, an
	 * {@link IllegalArgumentException} is thrown.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Double[][][] columnsToInsert = { { new Double[] { 1.0, 2.0, 3.0 } }, { new Double[] { 4.0, 5.0, 6.0 } } };
	 * Matrix<Double> updatedMatrix = matrix.insertColumnsBefore(1, columnsToInsert);
	 * // updatedMatrix now contains the original matrix with two new columns
	 * // inserted before the second column
	 * </pre>
	 * </p>
	 *
	 * @param indexBefore The index before which to insert the columns.
	 * @param y           The columns to insert, provided as varargs of arrays.
	 * @return The current matrix after inserting the columns.
	 * @throws IllegalArgumentException if the index is not valid.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T> insertColumnsBefore(int indexBefore, T[][]... y) {

		T[][] A = to2DArray();
		int totalColumns = A[0].length;
		for (T[][] x : y) {
			totalColumns += x[0].length;
		}

		T[][] result = Arrays.copyOf(A, A.length);
		for (int i = 0; i < result.length; i++) {
			result[i] = Arrays.copyOf(A[i], totalColumns);
		}

		int currentIndex = indexBefore;
		for (T[][] x : y) {
			for (int i = 0; i < x.length; i++) {
				System.arraycopy(x[i], 0, result[i], currentIndex, x[i].length);
				currentIndex += x[i].length;
			}
			currentIndex = indexBefore;
		}

		if (indexBefore < A[0].length) {
			for (int i = 0; i < A.length; i++) {
				System.arraycopy(A[i], indexBefore, result[i], currentIndex, A[i].length - indexBefore);
			}
		}

		return new Matrix<>(result);
	}

	/**
	 * Inserts columns after a specified index in the matrix.
	 * <p>
	 * This method inserts one or more columns after a specified index in the
	 * matrix. The columns to be inserted are provided as varargs of arrays, where
	 * each array represents a column. If the index is not valid, an
	 * {@link IllegalArgumentException} is thrown.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Double[][][] columnsToInsert = { { new Double[] { 1.0, 2.0, 3.0 } }, { new Double[] { 4.0, 5.0, 6.0 } } };
	 * Matrix<Double> updatedMatrix = matrix.insertColumnsAfter(1, columnsToInsert);
	 * // updatedMatrix now contains the original matrix with two new columns
	 * // inserted after the second column
	 * </pre>
	 * </p>
	 *
	 * @param indexAfter The index after which to insert the columns.
	 * @param y          The columns to insert, provided as varargs of arrays.
	 * @return The current matrix after inserting the columns.
	 * @throws IllegalArgumentException if the index is not valid.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T> insertColumnsAfter(int indexAfter, T[][]... y) {

		T[][] A = to2DArray();
		int totalColumns = A[0].length;
		for (T[][] x : y) {
			totalColumns += x[0].length;
		}

		T[][] result = Arrays.copyOf(A, A.length);
		for (int i = 0; i < result.length; i++) {
			result[i] = Arrays.copyOf(A[i], totalColumns);
		}

		int currentIndex = indexAfter + 1;
		for (T[][] x : y) {
			for (int i = 0; i < x.length; i++) {
				System.arraycopy(x[i], 0, result[i], currentIndex, x[i].length);
				currentIndex += x[i].length;
			}
			currentIndex = indexAfter + 1;
		}

		if (currentIndex < A[0].length) {
			for (int i = 0; i < A.length; i++) {
				System.arraycopy(A[i], currentIndex, result[i], currentIndex + y.length, A[i].length - currentIndex);
			}
		}

		return new Matrix<>(result);
	}

	/**
	 * Deletes a range of rows from the matrix.
	 * <p>
	 * This method removes a range of rows from the matrix, starting from the
	 * specified start index up to, but not including, the specified end index. If
	 * the indices are not valid, an {@link IllegalArgumentException} is thrown.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double> updatedMatrix = matrix.deleteRowsRange(1, 3);
	 * // updatedMatrix now contains the original matrix with the second row
	 * // removed
	 * </pre>
	 * </p>
	 *
	 * @param rowstart The starting row index (inclusive).
	 * @param rowend   The ending row index (exclusive).
	 * @return The current matrix after deleting the rows.
	 * @throws IllegalArgumentException if the indices are not valid.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T> deleteRowsRange(int rowstart, int rowend) {
		if (rowstart < 0 || rowend > nrows || rowstart >= rowend) {
			throw new IllegalArgumentException("Invalid row indices.");
		}

		Matrix<T> top = this.extractRowsRange(0, rowstart);
		Matrix<T> bottom = this.extractRowsRange(rowend, nrows);

		return top.concatenateVertically(bottom);
	}

	/**
	 * Deletes a range of columns from the matrix.
	 * <p>
	 * This method removes a range of columns from the matrix, starting from the
	 * specified start index up to, but not including, the specified end index. If
	 * the indices are not valid, an {@link IllegalArgumentException} is thrown.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double> updatedMatrix = matrix.deleteColumnsRange(1, 3);
	 * // updatedMatrix now contains the original matrix with the second and
	 * // third columns removed
	 * </pre>
	 * </p>
	 *
	 * @param colstart The starting column index (inclusive).
	 * @param colend   The ending column index (exclusive).
	 * @return The current matrix after deleting the columns.
	 * @throws IllegalArgumentException if the indices are not valid.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T> deleteColumnsRange(int colstart, int colend) {
		if (colstart < 0 || colend > ncols || colstart >= colend) {
			throw new IllegalArgumentException("Invalid column indices.");
		}

		Matrix<T> left = this.extractColumnsRange(0, colstart);
		Matrix<T> right = this.extractColumnsRange(colend, ncols);

		return left.concatenateVertically(right);
	}

	/**
	 * Deletes specific rows from the matrix.
	 * <p>
	 * This method removes specific rows from the matrix based on the provided
	 * indices. The indices are specified as varargs, where each value represents
	 * the index of a row to be removed. If any index is not valid, an
	 * {@link IllegalArgumentException} is thrown.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double> updatedMatrix = matrix.deleteRows(1, 2);
	 * // updatedMatrix now contains the original matrix with the second and
	 * // third rows removed
	 * </pre>
	 * </p>
	 *
	 * @param indices The list of the indices of the rows to delete.
	 * @return The current matrix after deleting the rows.
	 * @throws IllegalArgumentException if any index is not valid.
	 */
	public Matrix<T> deleteRows(int... indices) {

		T[][] A = to2DArray();
		int newLength = A.length - indices.length;
		T[][] result = Arrays.copyOf(A, newLength);

		Arrays.sort(indices);
		int j = 0, r = 0;
		for (int i = 0; i < A.length; i++) {
			if (j < indices.length && i == indices[j]) {
				j++;
				continue;
			}
			result[r++] = A[i];
		}

		return new Matrix<>(result);
	}

	/**
	 * Deletes specific columns from the matrix.
	 * <p>
	 * This method removes specific columns from the matrix based on the provided
	 * indices. The indices are specified as varargs, where each value represents
	 * the index of a column to be removed. If any index is not valid, an
	 * {@link IllegalArgumentException} is thrown.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double> updatedMatrix = matrix.deleteColumns(1, 2);
	 * // updatedMatrix now contains the original matrix with the second and
	 * // third columns removed
	 * </pre>
	 * </p>
	 *
	 * @param indices The list of the indices of the columns to delete.
	 * @return The current matrix after deleting the columns.
	 * @throws IllegalArgumentException if any index is not valid.
	 */
	public Matrix<T> deleteColumns(int... indices) {

		T[][] A = to2DArray();
		int newLength = A[0].length - indices.length;
		T[][] result = Arrays.copyOf(A, A.length);
		for (int i = 0; i < result.length; i++) {
			result[i] = Arrays.copyOf(A[i], newLength);
		}

		Arrays.sort(indices);
		int j = 0, r = 0;
		for (int i = 0; i < A[0].length; i++) {
			if (j < indices.length && i == indices[j]) {
				j++;
				continue;
			}
			for (T[] row : result) {
				row[r] = A[row.length][i];
			}
			r++;
		}

		return new Matrix<>(result);
	}

	/**
	 * Resizes the matrix to the specified dimensions.
	 * <p>
	 * This method adjusts the size of the matrix to the specified number of rows
	 * and columns. It creates a new array with the new dimensions and copies the
	 * elements from the old array to the new array, preserving as much of the
	 * original data as possible. If the new dimensions are larger than the
	 * original, the new elements are initialized with the default value for the
	 * type of the matrix's elements.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * matrix.resize(2, 2);
	 * // matrix now has dimensions 2x2
	 * </pre>
	 * </p>
	 *
	 * @param newNrows The new number of rows.
	 * @param newNcols The new number of columns.
	 */
	@SuppressWarnings("unchecked")
	public void resize(int newNrows, int newNcols) {
		// Create a new array with the new dimensions
		T[] newArray = (T[]) new Number[newNrows * newNcols];

		// Determine the minimum number of rows and columns to copy
		int minNrows = Math.min(nrows, newNrows);
		int minNcols = Math.min(ncols, newNcols);

		// Copy the elements from the old array to the new array
		for (int i = 0; i < minNrows; i++) {
			for (int j = 0; j < minNcols; j++) {
				newArray[i * newNcols + j] = this.array[i * ncols + j];
			}
		}

		// Update the array and dimensions
		this.array = newArray;
		this.nrows = newNrows;
		this.ncols = newNcols;
	}

	/**
	 * Swaps or permutes two rows in the matrix.
	 * <p>
	 * This method swaps the positions of two specified rows in the matrix. It is
	 * useful for reordering rows without needing to manually copy and paste data.
	 * If the indices are not valid, an {@link IllegalArgumentException} is thrown.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * matrix.swapRows(0, 2);
	 * // The first and third rows of the matrix are now swapped
	 * </pre>
	 * </p>
	 *
	 * @param row1 The index of the first row.
	 * @param row2 The index of the second row.
	 * @throws IllegalArgumentException if the row indices are not valid.
	 */
	public void swapRows(int row1, int row2) {
		// Check if the row indices are valid
		if (row1 < 0 || row1 >= nrows || row2 < 0 || row2 >= nrows) {
			throw new IllegalArgumentException("Invalid row indices.");
		}

		// Calculate the offsets for the two rows in the array
		int offset1 = row1 * ncols;
		int offset2 = row2 * ncols;

		// Swap the elements of the two rows
		for (int i = 0; i < ncols; i++) {
			T temp = array[offset1 + i];
			array[offset1 + i] = array[offset2 + i];
			array[offset2 + i] = temp;
		}
	}

	/**
	 * Swaps or permutes two columns in the matrix.
	 * <p>
	 * This method swaps the positions of two specified columns in the matrix. It is
	 * useful for reordering columns without needing to manually copy and paste
	 * data. If the indices are not valid, an {@link IllegalArgumentException} is
	 * thrown.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * matrix.swapCols(0, 2);
	 * // The first and third columns of the matrix are now swapped
	 * </pre>
	 * </p>
	 *
	 * @param col1 The index of the first column.
	 * @param col2 The index of the second column.
	 * @throws IllegalArgumentException if the column indices are not valid.
	 */
	public void swapCols(int col1, int col2) {
		// Check if the column indices are valid
		if (col1 < 0 || col1 >= ncols || col2 < 0 || col2 >= ncols) {
			throw new IllegalArgumentException("Invalid column indices.");
		}

		// Swap the elements of the two columns
		for (int i = 0; i < nrows; i++) {
			T temp = array[i * ncols + col1];
			array[i * ncols + col1] = array[i * ncols + col2];
			array[i * ncols + col2] = temp;
		}
	}

	/**
	 * Shuffles the rows of the matrix.
	 * <p>
	 * This method shuffles the rows of the matrix a specified number of times. Each
	 * shuffle involves swapping each row with a randomly selected row. This method
	 * is useful for randomizing the order of rows in the matrix.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * matrix.shuffleRows(5);
	 * // The rows of the matrix have been shuffled 5 times
	 * </pre>
	 * </p>
	 *
	 * @param times The number of times to shuffle.
	 */
	public void shuffleRows(int times) {
		Random rand = new Random();
		for (int t = 0; t < times; t++) {
			for (int i = 0; i < nrows; i++) {
				int j = rand.nextInt(nrows);
				swapRows(i, j);
			}
		}
	}

	/**
	 * Shuffles the columns of the matrix.
	 * <p>
	 * This method shuffles the columns of the matrix a specified number of times.
	 * Each shuffle involves swapping each column with a randomly selected column.
	 * This method is useful for randomizing the order of columns in the matrix.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * matrix.shuffleColumns(5);
	 * // The columns of the matrix have been shuffled 5 times
	 * </pre>
	 * </p>
	 *
	 * @param times The number of times to shuffle.
	 */
	public void shuffleColumns(int times) {
		Random rand = new Random();
		for (int t = 0; t < times; t++) {
			for (int i = 0; i < ncols; i++) {
				int j = rand.nextInt(ncols);
				swapCols(i, j);
			}
		}
	}

	/**
	 * Shuffles the values within a specified row of the matrix.
	 * <p>
	 * This method shuffles the values within a specified row of the matrix a
	 * specified number of times. Each shuffle involves swapping each value in the
	 * row with a randomly selected value within the same row. This method is useful
	 * for randomizing the order of values within a specific row.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * matrix.shuffleRowValues(0, 5);
	 * // The values within the first row of the matrix have been shuffled 5
	 * // times
	 * </pre>
	 * </p>
	 *
	 * @param row   The index of the row.
	 * @param times The number of times to shuffle.
	 */
	public void shuffleRowValues(int row, int times) {
		Random rand = new Random();
		for (int t = 0; t < times; t++) {
			for (int i = 0; i < ncols; i++) {
				int j = rand.nextInt(ncols);
				T temp = array[row * ncols + i];
				array[row * ncols + i] = array[row * ncols + j];
				array[row * ncols + j] = temp;
			}
		}
	}

	/**
	 * Shuffles the values within a specified column of the matrix.
	 * <p>
	 * This method shuffles the values within a specified column of the matrix a
	 * specified number of times. Each shuffle involves swapping each value in the
	 * column with a randomly selected value within the same column. This method is
	 * useful for randomizing the order of values within a specific column.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * matrix.shuffleColumnValues(0, 5);
	 * // The values within the first column of the matrix have been shuffled 5
	 * // times
	 * </pre>
	 * </p>
	 *
	 * @param col   The index of the column.
	 * @param times The number of times to shuffle.
	 */
	public void shuffleColumnValues(int col, int times) {
		Random rand = new Random();
		for (int t = 0; t < times; t++) {
			for (int i = 0; i < nrows; i++) {
				int j = rand.nextInt(nrows);
				T temp = array[i * ncols + col];
				array[i * ncols + col] = array[j * ncols + col];
				array[j * ncols + col] = temp;
			}
		}
	}

	/**
	 * Shuffles the values in the matrix.
	 * <p>
	 * This method shuffles the values in the entire matrix a specified number of
	 * times. Each shuffle involves swapping each value in the matrix with a
	 * randomly selected value from anywhere in the matrix. This method is useful
	 * for randomizing the order of all values in the matrix.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * matrix.shuffleValues(5);
	 * // The values in the matrix have been shuffled 5 times
	 * </pre>
	 * </p>
	 *
	 * @param times The number of times to shuffle.
	 */
	public void shuffleValues(int times) {
		Random rand = new Random();
		for (int t = 0; t < times; t++) {
			for (int i = 0; i < nrows * ncols; i++) {
				int j = rand.nextInt(nrows * ncols);
				T temp = array[i];
				array[i] = array[j];
				array[j] = temp;
			}
		}
	}

	/**
	 * Shuffles the values within specific rows of the matrix.
	 * <p>
	 * This method shuffles the values within specified rows of the matrix a
	 * specified number of times. Each shuffle involves swapping each value in the
	 * specified rows with a randomly selected value within the same row. This
	 * method is useful for randomizing the order of values within specific rows.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * matrix.shuffleRowValues(5, 0, 2);
	 * // The values within the first and third rows of the matrix have been
	 * // shuffled 5 times
	 * </pre>
	 * </p>
	 *
	 * @param times The number of times to shuffle.
	 * @param row   The indices of the rows.
	 */
	public void shuffleRowValues(int times, int... row) {
		for (int r : row) {
			if (r < 0 || r >= nrows) {
				throw new IllegalArgumentException("Invalid row index.");
			}
			for (int t = 0; t < times; t++) {
				for (int i = 0; i < ncols; i++) {
					Random rand = new Random();
					int j = rand.nextInt(ncols);
					T temp = array[r * ncols + i];
					array[r * ncols + i] = array[r * ncols + j];
					array[r * ncols + j] = temp;
				}
			}
		}
	}

	/**
	 * Shuffles the values within specific columns of the matrix.
	 * <p>
	 * This method shuffles the values within specified columns of the matrix a
	 * specified number of times. Each shuffle involves swapping each value in the
	 * specified columns with a randomly selected value within the same column. This
	 * method is useful for randomizing the order of values within specific columns.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * matrix.shuffleColumnValues(5, 0, 2);
	 * // The values within the first and third columns of the matrix have been
	 * // shuffled 5 times
	 * </pre>
	 * </p>
	 *
	 * @param times The number of times to shuffle.
	 * @param col   The indices of the columns to shuffle.
	 */
	public void shuffleColumnValues(int times, int... col) {
		for (int c : col) {
			if (c < 0 || c >= ncols) {
				throw new IllegalArgumentException("Invalid column index.");
			}
			for (int t = 0; t < times; t++) {
				for (int i = 0; i < nrows; i++) {
					Random rand = new Random();
					int j = rand.nextInt(nrows);
					T temp = array[i * ncols + c];
					array[i * ncols + c] = array[j * ncols + c];
					array[j * ncols + c] = temp;
				}
			}
		}
	}

	/*******************************************
	 * Matrix BASIC OPERATIONS
	 ********************************************/

	/**
	 * Adds a scalar to each element of the matrix.
	 * <p>
	 * This method adds a specified scalar value to each element of the matrix,
	 * returning a new matrix with the result. The operation is element-wise,
	 * meaning that the scalar is added to each element individually.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double> result = matrix.plus(2.0);
	 * // result now contains the original matrix with each element increased by
	 * // 2.0
	 * </pre>
	 * </p>
	 *
	 * @param scalarK The scalar to add.
	 * @return The current matrix after adding the scalar.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T> plus(T scalarK) {
		Matrix<T> result = new Matrix<>(this.nrows, this.ncols);
		for (int i = 0; i < this.array.length; i++) {
			T value = (T) Double.valueOf(this.array[i].doubleValue() + scalarK.doubleValue());
			result.array[i] = value;
		}
		return result;
	}

	/**
	 * Subtracts a scalar from each element of the matrix.
	 * <p>
	 * This method subtracts a specified scalar value from each element of the
	 * matrix, returning a new matrix with the result. The operation is
	 * element-wise, meaning that the scalar is subtracted from each element
	 * individually.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double> result = matrix.minus(2.0);
	 * // result now contains the original matrix with each element decreased by
	 * // 2.0
	 * </pre>
	 * </p>
	 *
	 * @param scalarK The scalar to subtract.
	 * @return The current matrix after subtracting the scalar.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T> minus(T scalarK) {
		Matrix<T> result = new Matrix<>(this.nrows, this.ncols);
		for (int i = 0; i < this.array.length; i++) {
			T value = (T) Double.valueOf(this.array[i].doubleValue() - scalarK.doubleValue());
			result.array[i] = value;
		}
		return result;
	}

	/**
	 * Adds another matrix to the current matrix.
	 * <p>
	 * This method adds another matrix to the current matrix, element-wise. The
	 * result is a new matrix where each element is the sum of the corresponding
	 * elements in the two matrices. If the dimensions of the two matrices do not
	 * match, an {@link IllegalArgumentException} is thrown.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix1 = new Matrix<>(3, 3);
	 * Matrix<Double> matrix2 = new Matrix<>(3, 3);
	 * // Assume matrix1 and matrix2 are initialized with Double values
	 * Matrix<Double> result = matrix1.plus(matrix2);
	 * // result now contains the sum of matrix1 and matrix2, element-wise
	 * </pre>
	 * </p>
	 *
	 * @param B The matrix to be added to the current matrix.
	 * @return A new matrix object that is the result of adding the input matrix to
	 *         the current matrix.
	 * @throws IllegalArgumentException If the dimensions of the two matrices do not
	 *                                  match.
	 */
	public Matrix<T> plus(Matrix<T> B) {
		// Check if the dimensions of the two matrices match
		if (this.nrows != B.nrows || this.ncols != B.ncols) {
			throw new IllegalArgumentException("Matrices dimensions do not match!");
		}

		// Create a new Matrix object to store the result
		Matrix<T> result = new Matrix<>(this.nrows, this.ncols);

		// Iterate over the 1D array directly
		for (int i = 0; i < this.array.length; i++) {
			// Use the add helper method to add corresponding elements in the two matrices
			T value = add(this.array[i], B.array[i]);
			// Set the calculated value in the result Matrix
			result.array[i] = value;
		}

		// Return the result Matrix
		return result;
	}

	/**
	 * Subtracts another matrix from the current matrix.
	 * <p>
	 * This method subtracts another matrix from the current matrix, element-wise.
	 * The result is a new matrix where each element is the difference of the
	 * corresponding elements in the two matrices. If the dimensions of the two
	 * matrices do not match, an {@link IllegalArgumentException} is thrown.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix1 = new Matrix<>(3, 3);
	 * Matrix<Double> matrix2 = new Matrix<>(3, 3);
	 * // Assume matrix1 and matrix2 are initialized with Double values
	 * Matrix<Double> result = matrix1.minus(matrix2);
	 * // result now contains the difference of matrix1 and matrix2,
	 * // element-wise
	 * </pre>
	 * </p>
	 *
	 * @param B The matrix to be subtracted from the current matrix.
	 * @return A new matrix object that is the result of subtracting the input
	 *         matrix from the current matrix.
	 * @throws IllegalArgumentException If the dimensions of the two matrices do not
	 *                                  match.
	 */
	public Matrix<T> minus(Matrix<T> B) {
		// Check if the dimensions of the two matrices match
		if (this.nrows != B.nrows || this.ncols != B.ncols) {
			throw new IllegalArgumentException("Matrices dimensions do not match!");
		}

		// Create a new Matrix object to store the result
		Matrix<T> result = new Matrix<>(this.nrows, this.ncols);

		// Iterate over the 1D array directly
		for (int i = 0; i < this.array.length; i++) {
			// Use the subtract helper method to subtract corresponding elements in the two
			// matrices
			T value = subtract(this.array[i], B.array[i]);
			// Set the calculated value in the result Matrix
			result.array[i] = value;
		}

		// Return the result Matrix
		return result;
	}

	/**
	 * Multiplies the current matrix by another matrix.
	 * <p>
	 * This method multiplies the current matrix by another matrix, returning the
	 * product as a new matrix. The operation is matrix multiplication, following
	 * the rules of linear algebra.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix1 = new Matrix<>(3, 3);
	 * Matrix<Double> matrix2 = new Matrix<>(3, 3);
	 * // Assume matrix1 and matrix2 are initialized with Double values
	 * Matrix<Double> result = matrix1.times(matrix2);
	 * // result now contains the product of matrix1 and matrix2
	 * </pre>
	 * </p>
	 *
	 * @param B The matrix to multiply by.
	 * @return The product of the two matrices.
	 */
	public Matrix<T> times(Matrix<T> B) {
		return ikjalgorithm1D(B);
	}

	/**
	 * Multiplies the current matrix by another matrix.
	 * <p>
	 * This method is an alias for {@link #times(Matrix<T> B)} and performs the same
	 * operation as {@link #times(Matrix<T> B)}, multiplying the current matrix by
	 * another matrix, returning the product as a new matrix.
	 * </p>
	 *
	 * @param B The matrix to multiply by.
	 * @return The product of the two matrices.
	 */
	public Matrix<T> multiply(Matrix<T> B) {
		return ikjalgorithm1D(B);
	}

	/**
	 * Computes the dot product (inner product) of the current matrix and a vector.
	 * <p>
	 * This method computes the dot product of the current matrix and a vector. The
	 * dot product is a scalar value that results from an operation that combines
	 * the elements of the matrix and the vector. The dimensions of the matrix and
	 * the vector must be compatible for the operation to be valid.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * Matrix<Double> vector = new Matrix<>(3, 1);
	 * // Assume matrix and vector are initialized with Double values
	 * Matrix<Double> result = matrix.dotProduct(vector);
	 * // result now contains the dot product of matrix and vector
	 * </pre>
	 * </p>
	 *
	 * @param vector The vector to compute the dot product with.
	 * @return The dot product of the matrix and the vector.
	 * @throws IllegalArgumentException if the dimensions of the matrix and the
	 *                                  vector are not compatible for the dot
	 *                                  product operation.
	 */
	public Matrix<T> dotProduct(Matrix<T> vector) {
		// Check if the dimensions of the Matrix and the vector are compatible
		// for the
		// dot product operation
		if (this.ncols != vector.nrows || vector.ncols != 1) {
			throw new IllegalArgumentException(
					"Number of columns of the matrix must be equal to the number of rows of the vector, and the vector must be a column vector.");
		}

		// Create a new Matrix to hold the result
		Matrix<T> C = new Matrix<>(this.nrows, 1);

		// Compute the dot product
		for (int i = 0; i < this.nrows; i++) {
			for (int j = 0; j < this.ncols; j++) {
				C.array[i] = add(C.array[i], mult(this.array[i * this.ncols + j], vector.array[j]));
			}
		}

		return C;
	}

	/**
	 * Multiplies each element of this matrix by the specified scalar value.
	 * <p>
	 * This method multiplies each element of the current matrix by a scalar value,
	 * returning a new matrix with the result. The scalar value is applied to each
	 * element individually.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double> result = matrix.dotProduct(2.0);
	 * // result now contains the original matrix with each element multiplied
	 * // by 2.0
	 * </pre>
	 * </p>
	 *
	 * @param scalar the scalar value to multiply each element of the matrix by
	 * @return a new Matrix instance with each element multiplied by the scalar
	 * @throws ClassCastException if the scalar value cannot be converted to the
	 *                            type T
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T> dotProduct(T scalar) {
		T[][] result = (T[][]) new Number[nrows][ncols];

		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				result[i][j] = mult(to2DArray()[i][j], scalar);
			}
		}

		return new Matrix<>(result);
	}

	/**
	 * Calculates the dot product between two column vectors of the same length.
	 * <p>
	 * This method calculates the dot product between two column vectors, which are
	 * matrices with a single column. The dot product is a scalar value that results
	 * from the multiplication of the corresponding elements of the two vectors.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> vector1 = new Matrix<>(1, 3);
	 * Matrix<Double> vector2 = new Matrix<>(1, 3);
	 * // Assume vector1 and vector2 are initialized with Double values
	 * Double result = matrix.dotProduct(vector1, vector2);
	 * // result now contains the dot product of vector1 and vector2
	 * </pre>
	 * </p>
	 *
	 * @param <T>     the type of the elements in the vectors, must extend Number
	 * @param vector1 the first column vector
	 * @param vector2 the second column vector
	 * @return the dot product between the two vectors
	 * @throws UnsupportedOperationException if the vectors have different lengths
	 *                                       or are not column vectors
	 * @throws ArithmeticException           if any arithmetic operation results in
	 *                                       an overflow or underflow
	 */
	@SuppressWarnings("unchecked")
	public T dotProduct(Matrix<T> vector1, Matrix<T> vector2)
			throws UnsupportedOperationException, ArithmeticException {
		if (vector1.getNrows() != 1 || vector2.getNcols() != 1 || vector1.getNcols() != vector2.getNrows()) {
			throw new UnsupportedOperationException(
					"Matrices must be column vectors with the same length for dot product.");
		}

		Number result = 0;

		for (int i = 0; i < vector1.getNcols(); i++) {
			result = result.doubleValue() + vector1.get(0, i).doubleValue() * vector2.get(i, 0).doubleValue();
		}

		return (T) result;
	}

	/**
	 * Computes the opposite (negation) of each element in the matrix.
	 * <p>
	 * This method creates a new matrix where each element is the negation of the
	 * corresponding element in the original matrix. The operation is element-wise,
	 * meaning that each element in the matrix is negated individually.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double> result = matrix.oppose();
	 * // result now contains the negation of each element in the original
	 * // matrix
	 * </pre>
	 * </p>
	 *
	 * @return The matrix with each element negated.
	 */
	public Matrix<T> oppose() {
		Matrix<T> B = new Matrix<>(nrows, ncols);
		T[] array1D = B.getArray();

		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				array1D[i * ncols + j] = negate(array[i * ncols + j]);
			}
		}

		return B;
	}

	/**
	 * Performs the transpose of the matrix in an optimized way.
	 * <p>
	 * This method efficiently transposes the matrix, swapping its rows with its
	 * columns. The transpose operation is a fundamental operation in linear algebra
	 * that involves flipping a matrix over its diagonal. This method is optimized
	 * for performance.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double> result = matrix.fasttranspose();
	 * // result now contains the transposed matrix
	 * </pre>
	 * </p>
	 *
	 * @return The transposed matrix.
	 */
	@SuppressWarnings("unchecked")
	public final Matrix<T> fasttranspose() {
		T[][] result = (T[][]) Array.newInstance(array.getClass().getComponentType(), ncols, nrows);

		for (int r = ncols; --r >= 0;) {
			for (int c = nrows; --c >= 0;) {
				result[r][c] = array[c * ncols + r];
			}
		}

		return new Matrix<>(result);
	}

	/**
	 * Performs the transpose of the matrix in a naive way.
	 * <p>
	 * This method naively transposes the matrix, swapping its rows with its
	 * columns. The transpose operation is a fundamental operation in linear algebra
	 * that involves flipping a matrix over its diagonal. This method is
	 * straightforward but may be less efficient than optimized methods.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double> result = matrix.transpose();
	 * // result now contains the transposed matrix
	 * </pre>
	 * </p>
	 *
	 * @return The transposed matrix.
	 */
	public Matrix<T> transpose() {
		Matrix<T> C = new Matrix<>(ncols, nrows);
		T[] array1D = C.getArray();

		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				array1D[j * nrows + i] = array[i * ncols + j];
			}
		}

		return C;
	}

	/***********************************************
	 * ELEMENT-WISE BASIC OPERATIONS
	 *********************************************/

	/**
	 * Divides each element of the current matrix by the corresponding element of
	 * another matrix.
	 * <p>
	 * This method performs element-wise division of the current matrix by another
	 * matrix. Each element in the current matrix is divided by the corresponding
	 * element in the other matrix. The result is a new matrix with the division
	 * results.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix1 = new Matrix<>(3, 3);
	 * Matrix<Double> matrix2 = new Matrix<>(3, 3);
	 * // Assume matrix1 and matrix2 are initialized with Double values
	 * Matrix<Double> result = matrix1.divElementByElement(matrix2);
	 * // result now contains the element-wise division of matrix1 by matrix2
	 * </pre>
	 * </p>
	 *
	 * @param B The right operand matrix.
	 * @return The result matrix after element-wise division.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T> divElementByElement(Matrix<T> B) {
		T[] arrayB1D = B.getArray();
		T[] result1D = (T[]) new Object[nrows * ncols];

		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				T divisor = arrayB1D[i * ncols + j];
				// Check for division by zero
				if (divisor != null && !divisor.equals(0)) {
					result1D[i * ncols + j] = divisor;
				} else {
					// Handle division by zero (you can set a default value or throw an exception)
					result1D[i * ncols + j] = null; // Set a default value
					// Alternatively, throw an exception
					// throw new ArithmeticException("Division by zero");
				}
			}
		}
		return new Matrix<>(result1D, 1);
	}

	/**
	 * Divides each element of the current matrix by a scalar value.
	 * <p>
	 * This method performs element-wise division of the current matrix by a scalar
	 * value. Each element in the current matrix is divided by the scalar value. The
	 * result is a new matrix with the division results.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double> result = matrix.divElementByConstant(2.0);
	 * // result now contains the original matrix with each element divided by
	 * // 2.0
	 * </pre>
	 * </p>
	 *
	 * @param scalar The constant scalar value.
	 * @return The result matrix after element-wise division by the scalar.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T> divElementByConstant(T scalar) {
		T[] result1D = (T[]) new Number[nrows * ncols];

		// Check for division by zero
		if (scalar != null && !scalar.equals(0)) {
			for (int i = 0; i < nrows; i++) {
				for (int j = 0; j < ncols; j++) {
					result1D[i * ncols + j] = scalar;
				}
			}
		} else {
			// Handle division by zero (you can set a default value or throw an exception)
			for (int i = 0; i < result1D.length; i++) {
				result1D[i] = null; // Set a default value
			}
			// Alternatively, throw an exception
			// throw new ArithmeticException("Division by zero");
		}
		return new Matrix<>(result1D, 1);
	}

	/**
	 * Performs element-wise division of the current matrix by another matrix.
	 * <p>
	 * This method divides each element of the current matrix by the corresponding
	 * element of another matrix. The dimensions of the two matrices must match,
	 * otherwise an {@link IllegalArgumentException} is thrown.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix1 = new Matrix<>(3, 3);
	 * Matrix<Double> matrix2 = new Matrix<>(3, 3);
	 * // Assume matrix1 and matrix2 are initialized with Double values
	 * Matrix<Double> result = matrix1.elementWiseDivide(matrix2);
	 * // result now contains the element-wise division of matrix1 by matrix2
	 * </pre>
	 * </p>
	 *
	 * @param other The matrix to divide by.
	 * @return The resulting matrix after element-wise division.
	 * @throws IllegalArgumentException If the dimensions of the two matrices do not
	 *                                  match.
	 */
	public Matrix<T> elementWiseDivide(Matrix<T> other) {
		if (nrows != other.nrows || ncols != other.ncols) {
			throw new IllegalArgumentException("Matrices must have the same dimensions for element-wise division.");
		}
		Matrix<T> result = new Matrix<>(nrows, ncols);
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				result.set(i, j, divide(this.get(i, j), other.get(i, j)));
			}
		}
		return result;
	}

	/**
	 * Performs element-wise multiplication of the current matrix by another matrix.
	 * <p>
	 * This method multiplies each element of the current matrix by the
	 * corresponding element of another matrix. The dimensions of the two matrices
	 * must match, otherwise an {@link IllegalArgumentException} is thrown.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix1 = new Matrix<>(3, 3);
	 * Matrix<Double> matrix2 = new Matrix<>(3, 3);
	 * // Assume matrix1 and matrix2 are initialized with Double values
	 * Matrix<Double> result = matrix1.elementWiseMultiply(matrix2);
	 * // result now contains the element-wise multiplication of matrix1 and
	 * // matrix2
	 * </pre>
	 * </p>
	 *
	 * @param other The matrix to multiply by.
	 * @return The resulting matrix after element-wise multiplication.
	 * @throws IllegalArgumentException If the dimensions of the two matrices do not
	 *                                  match.
	 */
	public Matrix<T> elementWiseMultiply(Matrix<T> other) {
		if (nrows != other.nrows || ncols != other.ncols) {
			throw new IllegalArgumentException(
					"Matrices must have the same dimensions for element-wise multiplication.");
		}
		Matrix<T> result = new Matrix<>(nrows, ncols);
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				result.set(i, j, mult(this.get(i, j), other.get(i, j)));
			}
		}
		return result;
	}

	/**
	 * Performs element-wise addition of the current matrix by another matrix.
	 * <p>
	 * This method adds each element of the current matrix to the corresponding
	 * element of another matrix. The dimensions of the two matrices must match,
	 * otherwise an {@link IllegalArgumentException} is thrown.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix1 = new Matrix<>(3, 3);
	 * Matrix<Double> matrix2 = new Matrix<>(3, 3);
	 * // Assume matrix1 and matrix2 are initialized with Double values
	 * Matrix<Double> result = matrix1.elementWiseAdd(matrix2);
	 * // result now contains the element-wise addition of matrix1 and matrix2
	 * </pre>
	 * </p>
	 *
	 * @param other The matrix to add.
	 * @return The resulting matrix after element-wise addition.
	 * @throws IllegalArgumentException If the dimensions of the two matrices do not
	 *                                  match.
	 */
	public Matrix<T> elementWiseAdd(Matrix<T> other) {
		if (nrows != other.nrows || ncols != other.ncols) {
			throw new IllegalArgumentException("Matrices must have the same dimensions for element-wise addition.");
		}
		Matrix<T> result = new Matrix<>(nrows, ncols);
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				result.set(i, j, add(this.get(i, j), other.get(i, j)));
			}
		}
		return result;
	}

	/**
	 * Performs element-wise subtraction of the current matrix by another matrix.
	 * <p>
	 * This method subtracts each element of the current matrix from the
	 * corresponding element of another matrix. The dimensions of the two matrices
	 * must match, otherwise an {@link IllegalArgumentException} is thrown.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix1 = new Matrix<>(3, 3);
	 * Matrix<Double> matrix2 = new Matrix<>(3, 3);
	 * // Assume matrix1 and matrix2 are initialized with Double values
	 * Matrix<Double> result = matrix1.elementWiseSubtract(matrix2);
	 * // result now contains the element-wise subtraction of matrix1 and
	 * // matrix2
	 * </pre>
	 * </p>
	 *
	 * @param other The matrix to subtract.
	 * @return The resulting matrix after element-wise subtraction.
	 * @throws IllegalArgumentException If the dimensions of the two matrices do not
	 *                                  match.
	 */
	public Matrix<T> elementWiseSubtract(Matrix<T> other) {
		if (nrows != other.nrows || ncols != other.ncols) {
			throw new IllegalArgumentException("Matrices must have the same dimensions for element-wise subtraction.");
		}
		Matrix<T> result = new Matrix<>(nrows, ncols);
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				result.set(i, j, subtract(this.get(i, j), other.get(i, j)));
			}
		}
		return result;
	}

	/**********************************************
	 * NORMS OR MAGNITUDES OR LENGTHS OR DISTANCES
	 **********************************************/

	/**
	 * Computes the lp-norm of the matrix.
	 * <p>
	 * The lp-norm, also known as the L^p norm, is a measure of the magnitude of the
	 * matrix. It is calculated by summing the absolute values of the matrix
	 * elements raised to the power of p, and then taking the p-th root of the sum.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * double norm = matrix.lpNorm(2); // Computes the L^2 norm (Euclidean norm)
	 * </pre>
	 * </p>
	 *
	 * @param p the order of the norm (p >= 1)
	 * @return the lp-norm
	 */
	public double lpNorm(int p) {
		double sum = 0.0;
		for (T element : array) {
			sum += Math.pow(element.doubleValue(), p);
		}
		return Math.pow(sum, 1.0 / p);
	}

	/**
	 * Computes the l1-norm (Manhattan norm) of the matrix.
	 * <p>
	 * The l1-norm is a measure of the magnitude of the matrix, calculated as the
	 * sum of the absolute values of its elements.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * double norm = matrix.l1Norm(); // Computes the L^1 norm (Manhattan norm)
	 * </pre>
	 * </p>
	 *
	 * @return the l1-norm
	 */
	public double l1Norm() {
		return lpNorm(1);
	}

	/**
	 * Computes the l2-norm (Euclidean norm) of the matrix.
	 * <p>
	 * The l2-norm is a measure of the magnitude of the matrix, calculated as the
	 * square root of the sum of the squares of its elements.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * double norm = matrix.l2Norm(); // Computes the L^2 norm (Euclidean norm)
	 * </pre>
	 * </p>
	 *
	 * @return the l2-norm
	 */
	public double l2Norm() {
		return lpNorm(2);
	}

	/**
	 * Computes the infinity-norm of the matrix.
	 * <p>
	 * The infinity-norm is the maximum absolute value of the elements in the
	 * matrix. It is a measure of the magnitude of the matrix.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * double norm = matrix.infinityNorm(); // Computes the infinity-norm
	 * </pre>
	 * </p>
	 *
	 * @return the infinity-norm
	 */
	public double infinityNorm() {
		double maxNorm = 0.0;
		for (T element : array) {
			double absValue = Math.abs(element.doubleValue());
			if (absValue > maxNorm) {
				maxNorm = absValue;
			}
		}
		return maxNorm;
	}

	/**
	 * Computes the Frobenius norm of the matrix.
	 * <p>
	 * The Frobenius norm is the square root of the sum of the squares of the
	 * elements of the matrix. It is a measure of the magnitude of the matrix.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * double norm = matrix.frobeniusNorm(); // Computes the Frobenius norm
	 * </pre>
	 * </p>
	 *
	 * @return the Frobenius norm
	 */
	public double frobeniusNorm() {
		double sumOfSquares = 0.0;
		for (T element : array) {
			sumOfSquares += Math.pow(element.doubleValue(), 2);
		}
		return Math.sqrt(sumOfSquares);
	}

	/**
	 * Computes the pq-norm of the matrix.
	 * <p>
	 * The pq-norm is a generalization of the lp-norm, where the matrix is first
	 * transformed into a p-norm along the columns, and then the q-norm of the
	 * transformed matrix is calculated.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * double norm = matrix.pq_norm(2, 2); // Computes the 2,2-norm
	 * </pre>
	 * </p>
	 *
	 * @param p the order of the norm along the columns (p >= 1)
	 * @param q the order of the norm of the transformed matrix (q >= 1)
	 * @return the pq-norm
	 */
	public double pq_norm(int p, int q) {
		if (p == 0 || q == 0) {
			throw new IllegalArgumentException("p and q must be non-zero.");
		}

		double norm = 0.0;
		T[][] Matrix = this.to2DArray();

		// Calculate the p-norm along the columns
		for (int j = 0; j < ncols; j++) {
			double sum = 0.0;
			for (int i = 0; i < nrows; i++) {
				sum += Math.pow(Math.abs(Matrix[i][j].doubleValue()), p);
			}
			norm += Math.pow(sum, 1.0 / p);
		}

		return Math.pow(norm, 1.0 / q);
	}

	/**
	 * Computes the maximum absolute value of the matrix.
	 * <p>
	 * This method finds the maximum absolute value among all the elements of the
	 * matrix.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * double maxAbs = matrix.maxAbsValue(); // Finds the maximum absolute value in the matrix
	 * </pre>
	 * </p>
	 *
	 * @return the maximum absolute value
	 */
	public double maxAbsValue() {
		double maxAbs = Double.NEGATIVE_INFINITY;
		for (int i = 0; i < array.length; i++) {
			double absValue = Math.abs(array[i].doubleValue());
			if (absValue > maxAbs) {
				maxAbs = absValue;
			}
		}
		return maxAbs;
	}

	/**
	 * Calculates the Ky Fan k-norm of the matrix.
	 * <p>
	 * The Ky Fan k-norm is the sum of the k largest singular values of the matrix.
	 * It is a measure of the matrix's "spread" or "width" in its singular value
	 * decomposition.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * double kyFanNorm = matrix.kyFanNorm(2); // Computes the 2-norm of the matrix's singular values
	 * </pre>
	 * </p>
	 *
	 * @param k The number of largest singular values to sum.
	 * @return The Ky Fan k-norm of the matrix.
	 * @throws IllegalArgumentException if k is less than 1 or greater than the
	 *                                  number of singular values.
	 */
	public double kyFanNorm(int k) throws IllegalArgumentException {
		if (k < 1 || k > Math.min(nrows, ncols)) {
			throw new IllegalArgumentException("k must be between 1 and the number of singular values.");
		}
		// Calculate the singular values
		double[] singularValues = this.singularValues();

		// Sort the singular values in descending order
		Arrays.sort(singularValues);
		int n = singularValues.length;

		// Sum the k largest singular values
		double sum = 0.0;
		for (int i = n - 1; i >= n - k; i--) {
			sum += singularValues[i];
		}

		return sum;
	}

	/**
	 * Computes the trace of the matrix.
	 * <p>
	 * The trace of a matrix is the sum of the elements on its main diagonal. It is
	 * a measure of the matrix's "identity" or "self".
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * double trace = matrix.trace(); // Computes the trace of the matrix
	 * </pre>
	 * </p>
	 *
	 * @return the trace of the matrix
	 * @throws IllegalArgumentException if the matrix is not square
	 */
	public double trace() {
		if (nrows != ncols) {
			throw new IllegalArgumentException("Matrix must be square to calculate trace");
		}

		double sum = 0.0;
		for (int i = 0; i < nrows; i++) {
			sum += array[i * ncols + i].doubleValue();
		}
		return sum;
	}

	/**
	 * Creates an identity matrix with the given number of rows and columns.
	 * <p>
	 * An identity matrix is a square matrix with ones on the main diagonal and
	 * zeros elsewhere. It is often used in linear algebra to represent the identity
	 * transformation.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> identityMatrix = Matrix.identity(3, 3); // Creates a 3x3 identity matrix
	 * </pre>
	 * </p>
	 *
	 * @param nrows Number of rows.
	 * @param ncols Number of columns.
	 * @return The identity matrix.
	 * @throws IllegalArgumentException if the number of rows and columns are not
	 *                                  equal.
	 */
	public Matrix<T> identity(int nrows, int ncols) {
		// Create a new Matrix with the given dimensions
		Matrix<T> B = new Matrix<>(nrows, ncols);
		T[][] BArray = B.to2DArray();

		// Set the elements of the principal diagonal to one and all other elements to
		// zero
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				// BArray[i][j] = (i == j) ? (T) Integer.valueOf(1) : (T) Integer.valueOf(0);
				BArray[i][j] = (i == j) ? oneValue() : zeroValue();
			}
		}

		return B;
	}

	/**
	 * Creates an identity matrix with the given size.
	 * <p>
	 * An identity matrix is a square matrix with ones on the main diagonal and
	 * zeros elsewhere. This method creates an identity matrix of the specified
	 * size, where the size is the number of rows and columns.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> identityMatrix = Matrix.identity(3); // Creates a 3x3 identity matrix
	 * </pre>
	 * </p>
	 *
	 * @param size The number of rows (and columns) for the identity matrix.
	 * @return The identity matrix of the given size.
	 */
	public Matrix<T> identity(int size) {
		// Create a new Matrix with the given dimensions
		Matrix<T> B = new Matrix<>(size, size);
		T[][] BArray = B.to2DArray();

		// Set the elements of the principal diagonal to one and all other elements to
		// zero
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				BArray[i][j] = (i == j) ? oneValue() : zeroValue();
			}
		}

		return B;
	}

	/**
	 * Creates a matrix with the given number of rows and columns, where all
	 * elements are ones.
	 * <p>
	 * This method creates a matrix filled with ones, with the specified number of
	 * rows and columns.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> onesMatrix = Matrix.ones(3, 3); // Creates a 3x3 matrix filled with ones
	 * </pre>
	 * </p>
	 *
	 * @param nrows Number of rows.
	 * @param ncols Number of columns.
	 * @return The matrix of ones.
	 */
	public Matrix<T> ones(int nrows, int ncols) {
		// Create a new Matrix with the given dimensions
		Matrix<T> B = new Matrix<>(nrows, ncols);
		T[][] BArray = B.to2DArray();

		// Set all elements to one
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				// BArray[i][j] = (T) Integer.valueOf(1);
				BArray[i][j] = oneValue();
			}
		}

		return B;
	}

	/**
	 * Creates a matrix filled with zeros.
	 * <p>
	 * This method creates a matrix of the specified dimensions, with all elements
	 * set to zero.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> zerosMatrix = Matrix.zeros(3, 3); // Creates a 3x3 matrix filled with zeros
	 * </pre>
	 * </p>
	 *
	 * @param rows The number of rows for the matrix.
	 * @param cols The number of columns for the matrix.
	 * @return A matrix filled with zeros.
	 */
	public Matrix<T> zeros(int rows, int cols) {
		Matrix<T> mat = new Matrix<>(rows, cols);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				mat.set(i, j, zeroValue());
			}
		}
		return mat;
	}

	/**
	 * Creates a square identity matrix of a given size.
	 * <p>
	 * This method is an alias for {@link #identity(int size)} and serves the same
	 * purpose, creating a square identity matrix of the specified size.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> identityMatrix = Matrix.eye(3); // Creates a 3x3 identity matrix
	 * </pre>
	 * </p>
	 *
	 * @param size The number of rows (and columns) for the identity matrix.
	 * @return An identity matrix of the given size.
	 */
	public Matrix<T> eye(int size) {
		Matrix<T> ident = new Matrix<>(size, size);
		for (int i = 0; i < size; ++i) {
			ident.set(i, i, oneValue());
		}
		return ident;
	}

	/**
	 * Calculates the minor of a matrix.
	 * <p>
	 * The minor of a matrix is the determinant of the submatrix obtained by
	 * removing a specified row and column from the original matrix. This method
	 * calculates the minor by excluding the given row and column from the matrix.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double> minorMatrix = matrix.minor(0, 0); // Calculates the minor excluding row 0 and column 0
	 * </pre>
	 * </p>
	 *
	 * @param r The row to exclude.
	 * @param c The column to exclude.
	 * @return The minor of the matrix obtained by excluding the given row and
	 *         column.
	 */
	public Matrix<T> minor(int r, int c) {
		int m = nrows - 1;
		int n = ncols - 1;
		Matrix<T> M = new Matrix<>(m, n);

		// Copy the elements of the original Matrix, skipping the specified row
		// and column
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				int x = (i < r) ? i : i + 1;
				int y = (j < c) ? j : j + 1;
				M.set(i, j, get(x, y));
			}
		}
		return M;
	}

	/**
	 * Converts the current matrix into an upper (superior) triangular matrix.
	 * <p>
	 * This method transforms the current matrix into an upper triangular matrix by
	 * setting all elements below the main diagonal to zero.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double> upperTriangularMatrix = matrix.toUpperTriangular();
	 * </pre>
	 * </p>
	 *
	 * @return The upper triangular matrix.
	 */
	public Matrix<T> toUpperTriangular() {
		// Create a copy of the current Matrix
		Matrix<T> B = new Matrix<>(nrows, ncols, array);
		T[][] BArray = B.to2DArray();

		// Set all elements below the main diagonal to zero
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < i; j++) {
				BArray[i][j] = zeroValue();
			}
		}

		return B;
	}

	/**
	 * Converts the current matrix into a lower (inferior) triangular matrix.
	 * <p>
	 * This method transforms the current matrix into a lower triangular matrix by
	 * setting all elements above the main diagonal to zero.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double> lowerTriangularMatrix = matrix.toLowerTriangular();
	 * </pre>
	 * </p>
	 *
	 * @return The lower triangular matrix.
	 */
	public Matrix<T> toLowerTriangular() {
		// Create a copy of the current Matrix
		Matrix<T> B = new Matrix<>(nrows, ncols, array);
		T[][] BArray = B.to2DArray();

		// Set all elements above the main diagonal to zero
		for (int i = 0; i < nrows; i++) {
			for (int j = i + 1; j < ncols; j++) {
				BArray[i][j] = zeroValue();
			}
		}

		return B;
	}

	/**
	 * Converts the given matrix into a lower (inferior) triangular matrix.
	 * <p>
	 * This method transforms the given matrix into a lower triangular matrix by
	 * setting all elements above the main diagonal to zero.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double> lowerTriangularMatrix = Matrix.lowerTriangular(matrix);
	 * </pre>
	 * </p>
	 *
	 * @param B The matrix to convert.
	 * @return The lower triangular matrix.
	 */
	public Matrix<T> lowerTriangular(Matrix<T> B) {
		// Create a copy of the given Matrix
		Matrix<T> C = new Matrix<>(B.getNrows(), B.getNcols(), B.getArray());
		T[][] CArray = C.to2DArray();

		// Set all elements above the main diagonal to zero
		for (int i = 0; i < C.getNrows(); i++) {
			for (int j = i + 1; j < C.getNcols(); j++) {
				CArray[i][j] = zeroValue();
			}
		}

		return C;
	}

	/**
	 * Converts the given matrix into an upper (superior) triangular matrix.
	 * <p>
	 * This method transforms the given matrix into an upper triangular matrix by
	 * setting all elements below the main diagonal to zero.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double> upperTriangularMatrix = Matrix.upperTriangular(matrix);
	 * </pre>
	 * </p>
	 *
	 * @param B The matrix to convert.
	 * @return The upper triangular matrix.
	 */
	public Matrix<T> upperTriangular(Matrix<T> B) {
		// Create a copy of the given Matrix
		Matrix<T> C = new Matrix<>(B.getNrows(), B.getNcols(), B.getArray());
		T[][] CArray = C.to2DArray();

		// Set all elements below the main diagonal to zero
		for (int i = 0; i < C.getNrows(); i++) {
			for (int j = 0; j < i; j++) {
				CArray[i][j] = zeroValue();
			}
		}

		return C;
	}

	/**
	 * Retrieves the diagonal elements of the matrix.
	 * <p>
	 * This method returns the elements on the main diagonal of the matrix. It is
	 * applicable only to square matrices.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Double[] diagonal = matrix.diag(); // Retrieves the diagonal elements
	 * </pre>
	 * </p>
	 *
	 * @return A 1D array containing the diagonal elements.
	 * @throws IllegalArgumentException if the matrix is not square.
	 */
	@SuppressWarnings("unchecked")
	public T[] diag() {
		if (nrows != ncols) {
			throw new IllegalArgumentException("Matrix must be square.");
		}
		T[] diag = (T[]) new Number[nrows];
		for (int i = 0; i < nrows; i++) {
			diag[i] = get(i, i);
		}
		return diag;
	}

	/**
	 * Computes the cofactor matrix (also known as the comatrix) of the matrix.
	 * <p>
	 * The cofactor matrix is derived from the original matrix by calculating the
	 * determinant of the minor matrix for each element and then applying a sign
	 * change based on the element's position.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double> cofactorMatrix = matrix.comatrix(); // Computes the cofactor matrix
	 * </pre>
	 * </p>
	 *
	 * @return The cofactor matrix of the matrix.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T> comatrix() {
		// Create a new Matrix of the same size
		Matrix<T> B = new Matrix<>(nrows, ncols);
		T[][] BArray = B.to2DArray();

		// Iterate over each element of the Matrix
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				// Calculate the minor of the element at position (i, j)
				Matrix<T> minorMatrix = minor(i, j);
				// Calculate the determinant of the minor
				double minorDeterminant = minorMatrix.determinant();
				// Calculate the cofactor of the element at position (i, j)
				double cofactor = Math.pow(-1, i + j) * minorDeterminant;
				// Set the corresponding element in the coMatrix to the cofactor
				BArray[i][j] = (T) Double.valueOf(cofactor);
			}
		}

		return B;
	}

	/**
	 * Calculates the determinant of a square matrix using the minor() method for
	 * computation.
	 * <p>
	 * This method computes the determinant of a square matrix using the recursive
	 * definition of determinant, which involves calculating the determinant of the
	 * minor matrices for each element in the first row or column.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * double determinant = matrix.determinant(); // Computes the determinant
	 * </pre>
	 * </p>
	 *
	 * @return The determinant of the matrix.
	 * @throws IllegalArgumentException if the matrix is not square.
	 */
	public double determinant() {
		// Check if the Matrix is square
		if (nrows != ncols) {
			throw new IllegalArgumentException("Matrix must be square.");
		}
		// If the Matrix is 1x1, return the single value
		if (nrows == 1) {
			return array[0].doubleValue();
		}
		// If the Matrix is 2x2, calculate the determinant directly
		if (nrows == 2) {
			return array[0].doubleValue() * array[3].doubleValue() - array[1].doubleValue() * array[2].doubleValue();
		}
		// For larger matrices, use the recursive definition of determinant
		double sum = 0.0;
		for (int i = 0; i < ncols; i++) {
			Matrix<T> subMatrix = minor(0, i);
			sum += Math.pow(-1, i) * array[i].doubleValue() * subMatrix.determinant();
		}
		return sum;
	}

	/**
	 * Calculates the determinant of a square matrix without dependency on the
	 * minor() method.
	 * <p>
	 * This method is an alternative to {@link #determinant()} and calculates the
	 * determinant directly for matrices of size 1x1 and 2x2, or using a recursive
	 * definition for larger matrices, similar to the determinant() method but
	 * without explicitly calling the minor() method.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * double determinant = matrix.determinant2(); // Computes the determinant
	 * </pre>
	 * </p>
	 *
	 * @return The determinant of the matrix.
	 * @throws IllegalArgumentException if the matrix is not square.
	 */
	public double determinant2() {
		// Check if the Matrix is square
		if (nrows != ncols) {
			throw new IllegalArgumentException("Matrix must be square.");
		}
		// If the Matrix is 1x1, return the single value
		if (nrows == 1) {
			return array[0].doubleValue();
		}
		// If the Matrix is 2x2, calculate the determinant directly
		if (nrows == 2) {
			return array[0].doubleValue() * array[3].doubleValue() - array[1].doubleValue() * array[2].doubleValue();
		}
		// For larger matrices, use the recursive definition of determinant
		double sum = 0.0;
		for (int i = 0; i < ncols; i++) {
			Matrix<T> subMatrix = minor(0, i);
			sum += Math.pow(-1, i) * array[i].doubleValue() * subMatrix.determinant();
		}
		return sum;
	}

	/**
	 * Creates a permutation matrix of the given size, with a specified permutation
	 * of the rows.
	 * <p>
	 * A permutation matrix is a square matrix with ones on the main diagonal and
	 * zeros elsewhere, where the position of the ones is determined by the
	 * specified permutation.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * int[] permutation = { 2, 0, 1 };
	 * Matrix<Double> permutationMatrix = Matrix.permutationMatrix(3, permutation); // Creates a permutation matrix
	 * </pre>
	 * </p>
	 *
	 * @param size The size of the matrix.
	 * @param perm The permutation of the rows.
	 * @return The permutation matrix.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T> permutationMatrix(int size, int[] perm) {
		// Create a new Matrix with the given size, initialized to zero
		Matrix<T> P = zeros(size, size);
		T[][] PArray = P.to2DArray();

		// Set the elements of the principal diagonal to one according to the
		// permutation
		for (int i = 0; i < size; i++) {
			PArray[i][perm[i]] = (T) Integer.valueOf(1);
		}

		return P;
	}

	/**
	 * Creates a permutation matrix of the given size.
	 * <p>
	 * This method creates a permutation matrix with ones on the main diagonal and
	 * zeros elsewhere, where the size is specified.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> permutationMatrix = Matrix.permutationMatrix(3); // Creates a 3x3 permutation matrix
	 * </pre>
	 * </p>
	 *
	 * @param size The size of the matrix.
	 * @return The permutation matrix.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T> permutationMatrix(int size) {
		// Create a new Matrix with the given size, initialized to zero
		Matrix<T> P = zeros(size, size);
		T[][] PArray = P.to2DArray();

		// Set the elements of the principal diagonal to one
		for (int i = 0; i < size; i++) {
			PArray[i][i] = (T) Integer.valueOf(1);
		}

		return P;
	}

	/**
	 * Creates a permutation matrix of the given dimensions.
	 * <p>
	 * This method creates a permutation matrix with ones on the main diagonal and
	 * zeros elsewhere, where the number of rows and columns are specified.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> permutationMatrix = Matrix.permutationMatrix(3, 3); // Creates a 3x3 permutation matrix
	 * </pre>
	 * </p>
	 *
	 * @param nrows Number of rows.
	 * @param ncols Number of columns.
	 * @return The permutation matrix.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T> permutationMatrix(int nrows, int ncols) {
		// Create a new Matrix with the given size, initialized to zero
		Matrix<T> P = zeros(nrows, ncols);
		T[][] PArray = P.to2DArray();

		// Set the elements of the principal diagonal to one
		for (int i = 0; i < Math.min(nrows, ncols); i++) {
			PArray[i][i] = (T) Integer.valueOf(1);
		}

		return P;
	}

	/**
	 * Creates a permutation matrix of the same size as the current matrix.
	 * <p>
	 * This method creates a permutation matrix with ones on the main diagonal and
	 * zeros elsewhere, based on the dimensions of the current matrix.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double> permutationMatrix = matrix.permutationMatrix(); // Creates a permutation matrix of the same size
	 * </pre>
	 * </p>
	 *
	 * @return The permutation matrix.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T> permutationMatrix() {
		// Create a new Matrix with the same size as the current Matrix,
		// initialized to zero
		Matrix<T> P = zeros(nrows, ncols);
		T[][] PArray = P.to2DArray();

		// Set the elements of the principal diagonal to one
		for (int i = 0; i < Math.min(nrows, ncols); i++) {
			PArray[i][i] = (T) Integer.valueOf(1);
		}

		return P;
	}

	/*********************************
	 * MATRICES ADVANCED OPERATIONS
	 **********************************/

	/**
	 * Performs LU Decomposition on the current matrix.
	 * <p>
	 * LU Decomposition decomposes a square matrix into the product of a lower
	 * triangular matrix (L) and an upper triangular matrix (U). This method returns
	 * an array containing the L and U matrices.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double>[] luDecomposition = matrix.luDecomposition(); // Performs LU decomposition
	 * </pre>
	 * </p>
	 *
	 * @return An array containing at position 0 the L(ower Triangular matrix) and
	 *         at position 1 the U(pper Triangular matrix).
	 * @throws IllegalArgumentException if the matrix is not square.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T>[] luDecomposition() {
		// Ensure square Matrix
		if (nrows != ncols) {
			throw new IllegalArgumentException("LU decomposition only supported for square matrices.");
		}

		// Create L and U matrices
		Matrix<T> L = new Matrix<>(nrows, ncols);
		Matrix<T> U = new Matrix<>(nrows, ncols);

		// Iterate through the Matrix
		for (int i = 0; i < nrows; i++) {
			// Upper Triangular
			for (int k = i; k < ncols; k++) {
				T sum = (T) zeroValue(); // Potential type safety issue, fix casting
				for (int j = 0; j < i; j++) {
					sum = add(sum, mult(L.get(i, j), U.get(j, k)));
				}
				U.set(i, k, subtract(get(i, k), sum));
			}

			// Lower Triangular
			for (int k = i; k < ncols; k++) {
				if (i == k) {
					L.set(i, i, (T) oneValue()); // Potential type safety issue, fix casting
				} else {
					T sum = (T) zeroValue(); // Potential type safety issue, fix casting
					for (int j = 0; j < i; j++) {
						sum = add(sum, mult(L.get(k, j), U.get(j, i)));
					}

					// Handle potential division by zero
					if (U.get(i, i).doubleValue() == 0) {
						throw new ArithmeticException("Division by zero during LU decomposition");
					}

					L.set(k, i, divide(subtract(get(k, i), sum), U.get(i, i)));
				}
			}
		}

		return (Matrix<T>[]) new Matrix[] { L, U };
	}

	/**
	 * Performs QR decomposition using the Householder method.
	 * <p>
	 * The Householder method is a more complex but generally more stable and
	 * efficient way to perform QR decomposition compared to other methods like
	 * Givens or Gram-Schmidt. This method decomposes the matrix into an orthogonal
	 * matrix Q and an upper triangular matrix R.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double>[] qrDecomposition = matrix.qrDecompositionHouseholder(); // Performs QR decomposition using
	 * 																		// Householder
	 * </pre>
	 * </p>
	 *
	 * @return An array containing at position 0 the Q matrix and at position 1 the
	 *         R matrix.
	 * @throws IllegalArgumentException      if the matrix is not square.
	 * @throws UnsupportedOperationException if the operation is not supported for
	 *                                       the given type.
	 * @throws ArithmeticException           if an arithmetic error occurs during
	 *                                       the computation.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T>[] qrDecompositionHouseholder() throws UnsupportedOperationException, ArithmeticException {
		// Check if the matrix is square
		if (nrows != ncols) {
			throw new IllegalArgumentException("Matrix must be square to compute the QR decomposition");
		}

		// Initialize Q as an identity matrix and R as a copy of the original matrix
		Matrix<T> Q = new Matrix<>(nrows, ncols);
		Matrix<T> R = new Matrix<>(nrows, ncols);
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				Q.set(i, j, (i == j) ? oneValue() : zeroValue());
				R.set(i, j, array[i * ncols + j]);
			}
		}

		// Householder QR decomposition
		for (int k = 0; k < ncols; k++) {
			T norm = zeroValue();
			for (int i = k; i < nrows; i++) {
				norm = add(norm, mult(R.get(i, k), R.get(i, k)));
			}
			norm = squareRoot(norm);

			// T s = (R.get(k, k).doubleValue() > 0) ? negativeOneValue() : oneValue();
			T s = (R.get(k, k).doubleValue() > 0) ? negate(oneValue()) : oneValue();
			T u1 = subtract(R.get(k, k), mult(s, norm));
			T w = divide(u1, norm);

			// Create the Householder matrix
			Matrix<T> H = new Matrix<>(nrows, nrows);
			for (int i = 0; i < nrows; i++) {
				for (int j = 0; j < nrows; j++) {
					H.set(i, j, (i == j) ? oneValue() : zeroValue());
					if (i > k) {
						H.set(i, j, subtract(H.get(i, j), mult(mult(twoValue(), w), mult(R.get(i, k), R.get(j, k)))));
					}
				}
			}

			// Apply H to R
			R = H.multiply(R);

			// Apply H to Q
			Q = H.multiply(Q);
		}

		// Return Q and R as an array
//		return new Matrix[] { Q, R };
		return (Matrix<T>[]) new Matrix[] { Q, R };
	}

	/**
	 * Performs QR decomposition using the Givens method.
	 * <p>
	 * The Givens method is a simple and intuitive method for performing QR
	 * decomposition. However, it can be numerically unstable, especially for
	 * matrices with small or large off-diagonal elements. This method decomposes
	 * the matrix into an orthogonal matrix Q and an upper triangular matrix R.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double>[] qrDecomposition = matrix.qrDecompositionGivens(); // Performs QR decomposition using Givens
	 * </pre>
	 * </p>
	 *
	 * @return An array containing at position 0 the Q matrix and at position 1 the
	 *         R matrix.
	 * @throws IllegalArgumentException      if the matrix is not square.
	 * @throws UnsupportedOperationException if the operation is not supported for
	 *                                       the given type.
	 * @throws ArithmeticException           if an arithmetic error occurs during
	 *                                       the computation.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T>[] qrDecompositionGivens() throws UnsupportedOperationException, ArithmeticException {
		// Check for square Matrix
		if (nrows != ncols) {
			throw new UnsupportedOperationException("QR decomposition only supported for square matrices.");
		}

		Matrix<T> Q = new Matrix<>(nrows, ncols);
		Matrix<T> R = new Matrix<>(nrows, ncols);

		// Copy original Matrix to R
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				R.set(i, j, get(i, j));
			}
		}

		for (int k = 0; k < ncols - 1; k++) {
			for (int j = k + 1; j < ncols; j++) {
				// Calculate Givens rotation parameters
				T c = R.get(k, k);
				T s = R.get(k, j);
				T r = squareRoot(add(mult(c, c), mult(s, s)));

				// Update R
				R.set(k, k, r);
				R.set(k, j, zeroValue());
				R.set(j, j, mult(c, r));
				R.set(j, k, negate(s));

				// Update Q
				for (int i = 0; i < nrows; i++) {
					T q1 = Q.get(i, k);
					T q2 = Q.get(i, j);
					Q.set(i, k, add(mult(c, q1), mult(s, q2)));
					Q.set(i, j, subtract(mult(negate(s), q1), mult(c, q2)));
				}
			}
		}

//		return new Matrix[] { Q, R };
		return (Matrix<T>[]) new Matrix[] { Q, R };
	}

	/**
	 * Performs QR decomposition using the Gram-Schmidt method.
	 * <p>
	 * The Gram-Schmidt method is a classical method for orthonormalizing a set of
	 * vectors in an inner product space, most commonly the Euclidean space R^n. It
	 * is particularly efficient for sparse matrices because it allows for early
	 * termination when processing zeros. This method decomposes the matrix into an
	 * orthogonal matrix Q and an upper triangular matrix R.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double>[] qrDecomposition = matrix.qrDecompositionGramSchmidt(); // Performs QR decomposition using
	 * 																		// Gram-Schmidt
	 * </pre>
	 * </p>
	 *
	 * @return An array containing at position 0 the Q matrix and at position 1 the
	 *         R matrix.
	 * @throws IllegalArgumentException      if the matrix is not square.
	 * @throws UnsupportedOperationException if the operation is not supported for
	 *                                       the given type.
	 * @throws ArithmeticException           if an arithmetic error occurs during
	 *                                       the computation.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T>[] qrDecompositionGramSchmidt() throws UnsupportedOperationException, ArithmeticException {
		// Check for square Matrix
		if (nrows != ncols) {
			throw new UnsupportedOperationException("QR decomposition only supported for square matrices.");
		}

		Matrix<T> Q = new Matrix<>(nrows, ncols);
		Matrix<T> R = new Matrix<>(nrows, ncols);

		// Initialize Q vectors
		for (int i = 0; i < ncols; i++) {
			Q.set(i, i, (T) Double.valueOf(1.0)); // Set diagonal elements of Q to 1
		}

		// Gram-Schmidt orthogonalization
		for (int j = 0; j < ncols; j++) {
			for (int k = 0; k < j; k++) {
				T proj = (T) divide((T) dotProduct(Q.getColumn(k), Q.getColumn(j)),
						(T) dotProduct(Q.getColumn(k), Q.getColumn(k))); // Calculate projection
				Q.subtractColumn(j, Q.getColumn(k).dotProduct(proj)); // Subtract projection from current column
			}

			// Normalize the j-th column of Q
			Q.setColumn(j, normalize(Q.getColumn(j)));

			// Fill in R (upper triangular part)
			for (int i = 0; i < j; i++) {
				R.set(i, j, (T) dotProduct(Q.getColumn(i), Q.getColumn(j)));
			}
		}

//		return new Matrix[] { Q, R };
		return (Matrix<T>[]) new Matrix[] { Q, R };
	}

	private void subtractColumn(int j, Matrix<T> Matrix) {
		for (int i = 0; i < nrows; i++) {
			set(i, j, subtract(get(i, j), Matrix.getArray()[i]));
		}
	}

	/**
	 * Performs back substitution on the given upper triangular matrix and
	 * right-hand side vector.
	 * <p>
	 * Back substitution is used to solve systems of linear equations that have been
	 * transformed into an upper triangular form. This method iterates from the
	 * bottom row up, solving for each variable in turn.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> A = new Matrix<>(3, 3);
	 * Matrix<Double> b = new Matrix<>(3, 1);
	 * // Assume A and b are initialized with Double values
	 * Matrix<Double> x = Matrix.backSubstitution(A, b); // Solves for x in Ax = b
	 * </pre>
	 * </p>
	 *
	 * @param A The upper triangular matrix.
	 * @param b The right-hand side vector.
	 * @return The solution vector.
	 * @throws IllegalArgumentException if the matrix dimensions do not match the
	 *                                  right-hand side vector.
	 */
	public Matrix<T> backSubstitution(Matrix<T> A, Matrix<T> b) {
		int size = A.getNrows();
		Matrix<T> x = new Matrix<>(size, 1);

		for (int i = size - 1; i >= 0; i--) {
			T sum = (T) zeroValue();
			for (int j = i + 1; j < size; j++) {
				sum = add(sum, mult(A.get(i, j), x.get(j, 0)));
			}
			x.set(i, 0, divide(subtract(b.get(i, 0), sum), A.get(i, i)));
		}

		return x;
	}

	/**
	 * Performs forward substitution on the given lower triangular matrix and
	 * right-hand side vector.
	 * <p>
	 * Forward substitution is used to solve systems of linear equations that have
	 * been transformed into a lower triangular form. This method iterates from the
	 * top row down, solving for each variable in turn.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> A = new Matrix<>(3, 3);
	 * Matrix<Double> b = new Matrix<>(3, 1);
	 * // Assume A and b are initialized with Double values
	 * Matrix<Double> x = Matrix.forwardSubstitution(A, b); // Solves for x in Ax = b
	 * </pre>
	 * </p>
	 *
	 * @param A The lower triangular matrix.
	 * @param b The right-hand side vector.
	 * @return The solution vector.
	 * @throws IllegalArgumentException if the matrix dimensions do not match the
	 *                                  right-hand side vector.
	 */
	public Matrix<T> forwardSubstitution(Matrix<T> A, Matrix<T> b) {
		int size = A.getNrows();
		Matrix<T> x = new Matrix<>(size, 1);

		for (int i = 0; i < size; i++) {
			T sum = (T) zeroValue();
			for (int j = 0; j < i; j++) {
				sum = add(sum, mult(A.get(i, j), x.get(j, 0)));
			}
			x.set(i, 0, divide(subtract(b.get(i, 0), sum), A.get(i, i)));
		}

		return x;
	}

	/**
	 * Normalizes a column vector.
	 * <p>
	 * This method computes the norm of the given column vector and then divides
	 * each element of the vector by its norm, resulting in a unit vector (a vector
	 * of length 1) pointing in the same direction as the original vector.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> column = new Matrix<>(3, 1);
	 * // Assume column is initialized with Double values
	 * Matrix<Double> normalizedColumn = matrix.normalize(column); // Normalizes the column vector
	 * </pre>
	 * </p>
	 *
	 * @param column The column vector to normalize.
	 * @return The normalized column vector.
	 * @throws UnsupportedOperationException if the type is not supported.
	 */
	public Matrix<T> normalize(Matrix<T> column) {
		T norm = zeroValue();
		for (int i = 0; i < column.getNrows(); i++) {
			norm = add(norm, mult(column.get(i, 0), column.get(i, 0)));
		}
		norm = squareRoot(norm);
		for (int i = 0; i < column.getNrows(); i++) {
			column.set(i, 0, divide(column.get(i, 0), norm));
		}
		return column;
	}

	/**
	 * Solves a system of linear equations represented by the matrix equation Ax =
	 * b.
	 * <p>
	 * This method solves the system of linear equations by performing either QR
	 * decomposition or LU decomposition, followed by either back substitution or
	 * forward substitution, depending on the specified decomposition method and
	 * substitution flag.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> A = new Matrix<>(3, 3);
	 * Matrix<Double> b = new Matrix<>(3, 1);
	 * // Assume A and b are initialized with Double values
	 * Matrix<Double> x = matrix.solveEquation(A, b, "QR", true); // Solves Ax = b using QR decomposition and back
	 * 															// substitution
	 * </pre>
	 * </p>
	 *
	 * @param A                       The matrix A in the equation.
	 * @param b                       The right-hand side vector in the equation.
	 * @param decompositionMethodName The name of the decomposition method to use
	 *                                ("QR" or "LU").
	 * @param substitution            true for back substitution, false for forward
	 *                                substitution.
	 * @return The solution vector x.
	 * @throws UnsupportedOperationException if the decomposition method is not
	 *                                       supported.
	 * @throws IllegalArgumentException      if the decomposition method name is
	 *                                       invalid.
	 */
	public Matrix<T> solveEquation(Matrix<T> A, Matrix<T> b, String decompositionMethodName, boolean substitution) {
		// Perform QR decomposition or LU decomposition based on the method name
		if (decompositionMethodName.equals("QR")) {
			// Perform QR decomposition
			A.qrDecompositionHouseholder(); // the more efficient and stable.
		} else if (decompositionMethodName.equals("LU")) {
			// Perform LU decomposition
			A.luDecomposition();
		} else {
			throw new IllegalArgumentException("Invalid decomposition method name. Only 'QR' and 'LU' are supported.");
		}

		// Solve the system of linear equations using back substitution or forward
		// substitution
		if (substitution) {
			// Assuming you have a method backSubstitution() that performs back substitution
			return backSubstitution(A, b);
		} else {
			// Assuming you have a method forwardSubstitution() that performs forward
			// substitution
			return forwardSubstitution(A, b);
		}
	}

	/**
	 * Calculates the inverse of the matrix using LU decomposition.
	 * <p>
	 * This method computes the inverse of the matrix by first performing LU
	 * decomposition and then solving the system of linear equations Ax = I for each
	 * column X of the inverse matrix, where A is the original matrix and I is the
	 * identity matrix.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double> inverseMatrix = matrix.InverseLU(); // Computes the inverse of the matrix
	 * </pre>
	 * </p>
	 *
	 * @return The inverse of the matrix.
	 * @throws RuntimeException if the matrix is not square.
	 */
	public Matrix<T> InverseLU() {
		// Perform LU decomposition
		luDecomposition();

		// Create an identity Matrix of the same size as the current
		// Matrix
		Matrix<T> I = identity(nrows, ncols);

		// Solve the system of linear equations AX = I for each column X of the inverse
		// Matrix
		Matrix<T> A_inv = new Matrix<>(nrows, ncols);
		for (int i = 0; i < ncols; i++) {
			Matrix<T> b = I.getColumn(i);
			A_inv.setColumn(i, solveEquation(this, b, "LU", true));
		}

		return A_inv;
	}

	/**
	 * Calculates the determinant of the matrix using LU decomposition.
	 * <p>
	 * This method computes the determinant of a square matrix by performing LU
	 * decomposition and then multiplying the diagonal elements of the upper
	 * triangular matrix from the LU decomposition.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * double determinant = matrix.determinantLU(); // Computes the determinant of the matrix
	 * </pre>
	 * </p>
	 *
	 * @return The determinant of the matrix.
	 * @throws RuntimeException if the matrix is not square.
	 */
	public T determinantLU() throws RuntimeException {
		if (nrows != ncols) {
			throw new RuntimeException("Matrix must be square.");
		}
		// Perform LU decomposition
		Matrix<T>[] luMatrices = this.luDecomposition();
		Matrix<T> upperTriangular = luMatrices[1];

		// Start with the identity value for multiplication
		T det = oneValue();

		// Multiply the diagonal elements
		for (int i = 0; i < nrows; i++) {
			det = mult(det, upperTriangular.get(i, i));
		}

		return det;
	}

	/**
	 * Performs Cholesky L (lower triangular) decomposition on the current matrix.
	 * <p>
	 * This method decomposes a symmetric, positive-definite matrix into the product
	 * of a lower triangular matrix L and its conjugate transpose. This
	 * decomposition is particularly efficient for numerical computation.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values and is symmetric,
	 * // positive-definite
	 * Matrix<Double> L = matrix.choleskyLDecomposition(); // Performs Cholesky L decomposition
	 * </pre>
	 * </p>
	 *
	 * @return The lower triangular matrix L.
	 * @throws IllegalArgumentException if the matrix is not square.
	 */
	public Matrix<T> choleskyLDecomposition() {
		if (nrows != ncols) {
			throw new IllegalArgumentException("Cholesky decomposition only supported for square matrices.");
		}

		Matrix<T> L = new Matrix<>(nrows, ncols);

		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j <= i; j++) {
				T sum = (T) zeroValue();

				if (j == i) { // Diagonal elements
					for (int k = 0; k < j; k++) {
						sum = add(sum, mult(L.get(j, k), L.get(j, k)));
					}
					L.set(j, j, sqrt(subtract(get(j, j), sum)));
				} else { // Off-diagonal elements
					for (int k = 0; k < j; k++) {
						sum = add(sum, mult(L.get(i, k), L.get(j, k)));
					}
					L.set(i, j, divide(subtract(get(i, j), sum), L.get(j, j)));
				}
			}
		}

		return L;
	}

	/**
	 * Performs Cholesky decomposition on the matrix.
	 * <p>
	 * This method decomposes a symmetric, positive-definite matrix into the product
	 * of a lower triangular matrix L and its conjugate transpose. It checks that
	 * the matrix is square, symmetric, and positive-definite before performing the
	 * decomposition.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values and is symmetric,
	 * // positive-definite
	 * Matrix<Double> L = matrix.choleskyDecomposition(); // Performs Cholesky decomposition
	 * </pre>
	 * </p>
	 *
	 * @return The lower triangular matrix L.
	 * @throws RuntimeException if the matrix is not square, symmetric, or positive
	 *                          definite.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T> choleskyDecomposition() throws RuntimeException {
		// Check if the Matrix is square
		if (nrows != ncols) {
			throw new RuntimeException("Matrix must be square for Cholesky decomposition.");
		}

		// Check if the Matrix is symmetric and positive definite
		if (!isSymmetricPositiveDefined()) {
			throw new RuntimeException("Matrix must be symmetric and positive definite for Cholesky decomposition.");
		}

		Matrix<T> L = new Matrix<>(nrows, ncols);

		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j <= i; j++) {
				double sum = 0.0;
				for (int k = 0; k < j; k++) {
					sum += L.get(i, k).doubleValue() * L.get(j, k).doubleValue();
				}

				if (i == j) {
					double val = get(i, i).doubleValue() - sum;
					// Ensure the matrix is positive definite
					if (val <= 0) {
						throw new RuntimeException("Matrix is not positive definite.");
					}
					L.set(i, j, (T) Double.valueOf(Math.sqrt(val)));
				} else {
					L.set(i, j, (T) Double.valueOf((get(i, j).doubleValue() - sum) / L.get(j, j).doubleValue()));
				}
			}
		}

		return L;
	}

	/**
	 * Performs QR decomposition on the matrix.
	 * <p>
	 * This method decomposes a matrix into the product of an orthogonal matrix Q
	 * and an upper triangular matrix R. The decomposition method used is
	 * "Householder" method.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double>[] qr = matrix.qrDecomposition("Householder"); // Performs QR decomposition using Householder
	 * Matrix<Double>[] Q = qr[0]; // get Q
	 * Matrix<Double>[] R = qr[1]; // get R
	 * </pre>
	 * </p>
	 *
	 * @return An array of two matrices [Q, R] where Q is an orthogonal matrix and R
	 *         is an upper triangular matrix.
	 * @throws Exception                     if the specified decomposition method
	 *                                       is not supported or does not exist.
	 * @throws UnsupportedOperationException if the matrix has more columns than
	 *                                       rows.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T>[] qrDecomposition() {
		int m = nrows;
		int n = ncols;
//        T[][] matrix = to2DArray();

		if (m < n) {
			throw new UnsupportedOperationException(
					"QR decomposition is not supported for matrices with more columns than rows.");
		}

		Matrix<T> Q = new Matrix<>(m, m);
		Matrix<T> R = this.clone(); // Ensure clone() is correctly implemented

		for (int k = 0; k < n; k++) {
			// Calculate the norm of the k-th column
			double norm = 0;
			for (int i = k; i < m; i++) {
				norm += Math.pow(R.to2DArray()[i][k].doubleValue(), 2);
			}
			norm = Math.sqrt(norm);

			if (R.to2DArray()[k][k].doubleValue() < 0) {
				norm = -norm;
			}

			// Update Q matrix
			for (int i = 0; i < m; i++) {
				Q.to2DArray()[i][k] = R.to2DArray()[i][k];
			}
			Q.to2DArray()[k][k] = (T) Double.valueOf(Q.to2DArray()[k][k].doubleValue() + norm);

			// Update R matrix
			double beta = -1.0 / norm;
			for (int i = k; i < m; i++) {
				R.to2DArray()[i][k] = (T) Double.valueOf(R.to2DArray()[i][k].doubleValue() * beta);
			}

			// Apply Householder transformation to the remaining columns of R
			for (int j = k + 1; j < n; j++) {
				double alpha = 0;
				for (int i = k; i < m; i++) {
					alpha += Q.to2DArray()[i][k].doubleValue() * R.to2DArray()[i][j].doubleValue();
				}

				alpha *= beta;

				for (int i = k; i < m; i++) {
					R.to2DArray()[i][j] = (T) Double
							.valueOf(R.to2DArray()[i][j].doubleValue() + alpha * Q.to2DArray()[i][k].doubleValue());
				}
			}
		}

//		return new Matrix[] { Q, R };
		return (Matrix<T>[]) new Matrix[] { Q, R };
	}

	/**
	 * Performs QR decomposition on the matrix by specifying the method name to use.
	 * <p>
	 * This method decomposes a matrix into the product of an orthogonal matrix Q
	 * and an upper triangular matrix R. The decomposition method can be specified
	 * as "Householder", "Givens", or "GramScmidt".
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double>[] qr = matrix.qrDecomposition("Householder"); // Performs QR decomposition using Householder
	 * Matrix<Double>[] Q = qr[0]; // get Q
	 * Matrix<Double>[] R = qr[1]; // get R
	 * </pre>
	 * </p>
	 *
	 * @param qrMethodName "Householder" | "Givens" | "GramScmidt"
	 * @return An array of two matrices [Q, R] where Q is an orthogonal matrix and R
	 *         is an upper triangular matrix.
	 * @throws Exception                     if the specified decomposition method
	 *                                       is not supported or does not exist.
	 * @throws UnsupportedOperationException if the matrix has more columns than
	 *                                       rows.
	 */
	public Matrix<T>[] qrDecomposition(String qrMethodName) throws Exception {
		if (qrMethodName.equalsIgnoreCase("Householder")) {
			return qrDecompositionHouseholder();
		} else if (qrMethodName.equalsIgnoreCase("Givens")) {
			return qrDecompositionGivens();
		} else if (qrMethodName.equalsIgnoreCase("GramScmidt")) {
			return qrDecompositionGramSchmidt();
		} else {
			throw new Exception(qrMethodName + " not yet implemented or doesn't exist. Check a linear algebra book.");
		}
	}

	/**
	 * Calculates the inverse of the matrix.
	 * <p>
	 * This method computes the inverse of the matrix by augmenting the original
	 * matrix with the identity matrix and then applying Gauss-Jordan elimination to
	 * transform the left side of the augmented matrix into the identity matrix.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double> inverseMatrix = matrix.inverse(); // Computes the inverse of the matrix
	 * </pre>
	 * </p>
	 *
	 * @return The inverse of the matrix.
	 * @throws RuntimeException if the matrix is not square or singular.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T> inverse() throws RuntimeException {
		// Check if the Matrix is square
		if (nrows != ncols) {
			throw new RuntimeException("Matrix must be square for inversion.");
		}

		// Create an identity Matrix of the same size as the original
		// Matrix
		Matrix<T> identity = identity(nrows);

		// Augment the original Matrix with the identity Matrix
		Matrix<T> augmentedMatrix = this.concatenateHorizontally(identity);

		// Apply Gauss-Jordan elimination to transform the left side into the identity
		// Matrix
		for (int i = 0; i < nrows; i++) {
			// Find pivot
			int pivotRow = i;
			for (int j = i + 1; j < nrows; j++) {
				if (Math.abs(augmentedMatrix.get(j, i).doubleValue()) > Math
						.abs(augmentedMatrix.get(pivotRow, i).doubleValue())) {
					pivotRow = j;
				}
			}

			// Swap rows to make the pivot the current row
			if (pivotRow != i) {
				augmentedMatrix.swapRows(i, pivotRow);
			}

			// Scale the pivot row to make the pivot element equal to 1
			T pivotValue = augmentedMatrix.get(i, i);
			if (pivotValue.doubleValue() == 0.0) {
				throw new RuntimeException("Matrix is singular.");
			}
			for (int j = 0; j < ncols * 2; j++) {
				augmentedMatrix.set(i, j, divide(augmentedMatrix.get(i, j), pivotValue));
			}

			// Eliminate other rows to make their i-th column elements equal to 0
			for (int j = 0; j < nrows; j++) {
				if (j != i) {
					T factor = augmentedMatrix.get(j, i);
					for (int k = 0; k < ncols * 2; k++) {
						T newValue = subtract(augmentedMatrix.get(j, k), mult(factor, augmentedMatrix.get(i, k)));
						augmentedMatrix.set(j, k, newValue);
					}
				}
			}
		}

		// Extract the inverted Matrix from the right side of the augmented
		// Matrix
		Matrix<T> invertedMatrix = new Matrix<>(nrows, ncols);
		for (int i = 0; i < nrows; i++) {
			System.arraycopy(augmentedMatrix.getArray(), i * ncols * 2 + ncols, invertedMatrix.getArray(), i * ncols,
					ncols);
		}

		return invertedMatrix;
	}

	/**
	 * Calculates the determinant of the matrix using the Householder QR
	 * decomposition method.
	 * <p>
	 * This method computes the determinant of a square matrix by first performing
	 * the Householder QR decomposition, which decomposes the matrix into an
	 * orthogonal matrix Q and an upper triangular matrix R. The determinant is then
	 * calculated by multiplying the diagonal elements of the upper triangular
	 * matrix R.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * double determinant = matrix.determinantByHouseholderQR(); // Computes the determinant using Householder QR
	 * </pre>
	 * </p>
	 *
	 * @return The determinant of the matrix.
	 * @throws RuntimeException if the matrix is not square.
	 */
	public T determinantByHouseholderQR() throws RuntimeException {
		if (nrows != ncols) {
			throw new RuntimeException("Matrix must be square.");
		}
		// Perform QR decomposition
		Matrix<T>[] qrMatrices = this.qrDecompositionHouseholder();
		Matrix<T> upperTriangular = qrMatrices[1];

		// Start with the identity value for multiplication
		T det = oneValue();

		// Multiply the diagonal elements
		for (int i = 0; i < nrows; i++) {
			det = mult(det, upperTriangular.get(i, i));
		}

		return det;
	}

	/**
	 * Calculates the determinant of the matrix using the Gram-Schmidt QR
	 * decomposition method.
	 * <p>
	 * This method computes the determinant of a square matrix by first performing
	 * the Gram-Schmidt QR decomposition, which decomposes the matrix into an
	 * orthogonal matrix Q and an upper triangular matrix R. The determinant is then
	 * calculated by multiplying the diagonal elements of the upper triangular
	 * matrix R.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * double determinant = matrix.determinantByQRGramSchmidt(); // Computes the determinant using Gram-Schmidt QR
	 * </pre>
	 * </p>
	 *
	 * @return The determinant of the matrix.
	 * @throws RuntimeException if the matrix is not square.
	 */
	public T determinantByQRGramSchmidt() throws RuntimeException {
		if (nrows != ncols) {
			throw new RuntimeException("Matrix must be square.");
		}
		// Perform QR decomposition
		Matrix<T>[] qrMatrices = this.qrDecompositionGramSchmidt();
		Matrix<T> upperTriangular = qrMatrices[1];

		// Start with the identity value for multiplication
		T det = oneValue();

		// Multiply the diagonal elements
		for (int i = 0; i < nrows; i++) {
			det = mult(det, upperTriangular.get(i, i));
		}

		return det;
	}

	/**
	 * Calculates the determinant of the matrix using the Givens QR decomposition
	 * method.
	 * <p>
	 * This method computes the determinant of a square matrix by first performing
	 * the Givens QR decomposition, which decomposes the matrix into an orthogonal
	 * matrix Q and an upper triangular matrix R. The determinant is then calculated
	 * by multiplying the diagonal elements of the upper triangular matrix R.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * double determinant = matrix.determinantByQRGivens(); // Computes the determinant using Givens QR
	 * </pre>
	 * </p>
	 *
	 * @return The determinant of the matrix.
	 * @throws RuntimeException if the matrix is not square.
	 */
	public T determinantByQRGivens() throws RuntimeException {
		if (nrows != ncols) {
			throw new RuntimeException("Matrix must be square.");
		}
		// Perform QR decomposition
		Matrix<T>[] qrMatrices = this.qrDecompositionGivens();
		Matrix<T> upperTriangular = qrMatrices[1];

		// Start with the identity value for multiplication
		T det = oneValue();

		// Multiply the diagonal elements
		for (int i = 0; i < nrows; i++) {
			det = mult(det, upperTriangular.get(i, i));
		}

		return det;
	}

	/**
	 * Inverts the matrix using the Gauss-Jordan elimination method.
	 * <p>
	 * This method computes the inverse of a square matrix by augmenting the
	 * original matrix with the identity matrix and then applying Gauss-Jordan
	 * elimination to transform the left side of the augmented matrix into the
	 * identity matrix.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double> inverseMatrix = matrix.inverseGaussJordan(); // Computes the inverse using Gauss-Jordan
	 * 															// elimination
	 * </pre>
	 * </p>
	 *
	 * @return The inverse matrix.
	 * @throws RuntimeException if the matrix is not square or singular.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T> inverseGaussJordan() throws RuntimeException {
		if (nrows != ncols) {
			throw new RuntimeException("Matrix must be square for inversion.");
		}

		// Identity Matrix of the same size as the original Matrix
		Matrix<T> identity = identity(nrows);

		// Augment the original Matrix with the identity Matrix
		Matrix<T> augmentedMatrix = this.concatenateHorizontally(identity);

		// Apply Gauss-Jordan elimination to transform the left side into the identity
		// Matrix
		for (int i = 0; i < nrows; i++) {
			// Find pivot
			int pivotRow = i;
			for (int j = i + 1; j < nrows; j++) {
				if (Math.abs(augmentedMatrix.get(j, i).doubleValue()) > Math
						.abs(augmentedMatrix.get(pivotRow, i).doubleValue())) {
					pivotRow = j;
				}
			}

			// Swap rows to make the pivot the current row
			if (pivotRow != i) {
				augmentedMatrix.swapRows(i, pivotRow);
			}

			// Scale the pivot row to make the pivot element equal to 1
			T pivotValue = augmentedMatrix.get(i, i);
			if (pivotValue.doubleValue() == 0.0) {
				throw new RuntimeException("Matrix is singular.");
			}
			for (int j = 0; j < ncols * 2; j++) {
				augmentedMatrix.set(i, j, divide(augmentedMatrix.get(i, j), pivotValue));
			}

			// Eliminate other rows to make their i-th column elements equal to 0
			for (int j = 0; j < nrows; j++) {
				if (j != i) {
					T factor = augmentedMatrix.get(j, i);
					for (int k = 0; k < ncols * 2; k++) {
						T newValue = subtract(augmentedMatrix.get(j, k), mult(factor, augmentedMatrix.get(i, k)));
						augmentedMatrix.set(j, k, newValue);
					}
				}
			}
		}

		// Extract the inverted Matrix from the right side of the augmented
		// Matrix
		Matrix<T> invertedMatrix = new Matrix<>(nrows, ncols);
		for (int i = 0; i < nrows; i++) {
			System.arraycopy(augmentedMatrix.getArray(), i * ncols * 2 + ncols, invertedMatrix.getArray(), i * ncols,
					ncols);
		}

		return invertedMatrix;
	}

	/**
	 * Reshapes the matrix to the specified number of rows and columns.
	 * <p>
	 * This method changes the dimensions of the matrix to the specified number of
	 * rows and columns. It ensures that the total number of elements in the
	 * reshaped matrix matches the total number of elements in the original matrix.
	 * If the total number of elements does not match, a {@link RuntimeException} is
	 * thrown.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * matrix.reshape(2, 2); // Reshapes the matrix to 2x2
	 * </pre>
	 * </p>
	 *
	 * @param newRows The new number of rows.
	 * @param newCols The new number of columns.
	 * @throws RuntimeException if the total number of elements in the reshaped
	 *                          matrix does not match the total number of elements
	 *                          in the original matrix.
	 */
	@SuppressWarnings("unchecked")
	public void reshape(int newRows, int newCols) throws RuntimeException {
		if (newRows * newCols != nrows * ncols) {
			throw new RuntimeException("Number of elements must remain the same after reshaping.");
		}

		// Create a new array with the new shape
		T[] newArray = (T[]) new Number[newRows * newCols];
		System.arraycopy(array, 0, newArray, 0, array.length);

		// Update the array and dimensions
		array = newArray;
		nrows = newRows;
		ncols = newCols;
	}

	/**
	 * Flattens the matrix into a 1D array.
	 * <p>
	 * This method returns the matrix's elements in a single array, effectively
	 * converting the matrix into a linearized form.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Double[] flattened = matrix.flatten(); // Flattens the matrix into a 1D array
	 * </pre>
	 * </p>
	 *
	 * @return The flattened 1D array.
	 */
	public T[] flatten() {
		// No need to create a new array, just return the existing one
		return array;
	}

	/**
	 * Performs the Jacobi eigenvalues decomposition iterative and basic algorithm.
	 * <p>
	 * This method computes the eigenvalues of the matrix using the Jacobi
	 * eigenvalues decomposition. It iteratively refines the matrix until
	 * convergence is reached or the maximum number of iterations is exceeded.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double> eigendecomposition = matrix.eigenvalueDecomposition(); // Computes the eigenvalues
	 * Double[][] eigenValuesVectorsArray = matrix.eigenvalueDecomposition().to2DArray(); // Computes the eigenvalues
	 * Double[] eigenvalues = eigenValuesVectorsArray[0]; // get the eigenvalues
	 * Double[] eigenvectors = eigenValuesVectorsArray[1]; // get the eigenvectors
	 * </pre>
	 * </p>
	 *
	 * @return The eigenvalues as a matrix.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T> eigenvalueDecomposition() {
		int dim = nrows;

		// Working Matrix M starts as a copy of A
		Number[][] M = new Number[dim][dim];
		for (int i = 0; i < dim; i++) {
			System.arraycopy(array, i * dim, M[i], 0, dim);
		}

		// These values affect performances. Be careful!
		double tolerance = 1e-10; // can be change for better results and improvements
		int maxIterations = 1000; // can be increased for more accuracy
		int counter = 0;

		// Main loop
		while (!converged(M, tolerance) && counter <= maxIterations) {
			// Identify largest off-diagonal element
			int idxI = -1, idxJ = -1;
			double maxOffDiag = Double.NEGATIVE_INFINITY;
			for (int i = 0; i < dim; i++) {
				for (int j = i + 1; j < dim; j++) {
					double currVal = Math.abs(M[i][j].doubleValue());
					if (currVal > maxOffDiag) {
						idxI = i;
						idxJ = j;
						maxOffDiag = currVal;
					}
				}
			}

			// Break early if all off-diagonal elements close enough to zero
			if (maxOffDiag < tolerance) {
				break;
			}

			// Calculate rotation angles
			double diff = M[idxI][idxI].doubleValue() - M[idxJ][idxJ].doubleValue();
			double cosTheta = diff
					/ Math.sqrt(diff * diff + 4 * M[idxI][idxJ].doubleValue() * M[idxI][idxJ].doubleValue());
			double sinTheta = -Math.signum(M[idxI][idxJ].doubleValue()) * Math.sqrt(1 - cosTheta * cosTheta);

			// Apply rotation
			for (int k = 0; k < dim; k++) {
				double tmp = M[k][idxI].doubleValue();
				M[k][idxI] = cosTheta * tmp - sinTheta * M[k][idxJ].doubleValue();
				M[k][idxJ] = sinTheta * tmp + cosTheta * M[k][idxJ].doubleValue();
			}

			counter++;
		}

		// Results
		Number[] eigValues = new Number[dim];
		Number[][] eigVecs = new Number[dim][dim];

		// Assign eigenvalues
		for (int i = 0; i < dim; i++) {
			eigValues[i] = M[i][i];
		}

		// Assign eigenvectors from M
		for (int i = 0; i < dim; i++) {
			eigVecs[i] = Arrays.copyOfRange(M[i], 0, dim);
		}

		// Normalize eigenvectors
		for (int i = 0; i < dim; i++) {
			double len = 0.0;
			for (int j = 0; j < dim; j++) {
				len += eigVecs[i][j].doubleValue() * eigVecs[i][j].doubleValue();
			}
			len = Math.sqrt(len);
			for (int j = 0; j < dim; j++) {
				eigVecs[i][j] = eigVecs[i][j].doubleValue() / len;
			}
		}

		// Return the eigenvalue decomposition as a 2D array
		T[][] eigenDecomposition = (T[][]) new Number[2][];
		eigenDecomposition[0] = (T[]) eigValues;
		eigenDecomposition[1] = (T[]) Arrays.stream(eigVecs).flatMap(Arrays::stream).toArray(Number[]::new); // convert
																												// 2D
		// array to 1D
		// easily

		return new Matrix<>(eigenDecomposition);
	}

	/**
	 * Computes the eigenvalues of the matrix.
	 * <p>
	 * This method calculates the eigenvalues of a square matrix by performing a QR
	 * decomposition and then using the diagonal elements of the resulting matrix.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Double[] eigenvalues = matrix.eigenvalues(); // Computes the eigenvalues
	 * </pre>
	 * </p>
	 *
	 * @return A 1D array containing the eigenvalues.
	 * @throws IllegalArgumentException if the matrix is not square.
	 */
	public T[] eigenvalues() {
		if (nrows != ncols) {
			throw new IllegalArgumentException("Matrix must be square.");
		}
		Matrix<T>[] qr = this.qrDecompositionHouseholder();
		Matrix<T> Q = qr[0];
		Matrix<T> R = qr[1];
		Matrix<T> A_next = R.multiply(Q);
		// The eigenvalues are the diagonal elements of A_next
		return A_next.diag();
	}

	/**
	 * Computes the eigenvectors of the matrix.
	 * <p>
	 * This method calculates the eigenvectors of a square matrix by performing a QR
	 * decomposition and then extracting the columns of the resulting matrix Q,
	 * which represent the eigenvectors.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double> eigenvectors = matrix.eigenvectors(); // Computes the eigenvectors
	 * </pre>
	 * </p>
	 *
	 * @return A 2D array where each row is an eigenvector.
	 * @throws IllegalArgumentException if the matrix is not square.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T> eigenvectors() {
		if (nrows != ncols) {
			throw new IllegalArgumentException("Matrix must be square.");
		}
		Matrix<T>[] qr = this.qrDecompositionHouseholder();
		Matrix<T> Q = qr[0];
//		Matrix<T> R = qr[1];
//		Matrix<T> A_next = R.multiply(Q);
		// The eigenvectors are the columns of Q
		T[][] eigenvectors = (T[][]) new Number[ncols][];
		for (int i = 0; i < ncols; i++) {
			eigenvectors[i] = Q.getColumn(i).to1DArray();
		}
		return new Matrix<>(eigenvectors);
	}

	/**
	 * Returns a subMatrix of the current matrix.
	 * <p>
	 * This method extracts a subMatrix from the current matrix, starting from the
	 * first row and ending at the specified row. The subMatrix will have the same
	 * number of columns as the original matrix.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double> subMatrix = matrix.getSubMatrix(2); // Extracts a 3x3 subMatrix ending at row 2
	 * </pre>
	 * </p>
	 *
	 * @param endRow The row index where the subMatrix ends.
	 * @return The subMatrix.
	 * @throws IllegalArgumentException if the endRow index is not valid.
	 */
	public Matrix<T> getSubMatrix(int endRow) {
		if (endRow < 0 || endRow >= nrows) {
			throw new IllegalArgumentException("End row index must be between 0 and " + (nrows - 1));
		}

		Matrix<T> subMatrix = new Matrix<>(endRow + 1, ncols);
		for (int i = 0; i <= endRow; i++) {
			for (int j = 0; j < ncols; j++) {
				subMatrix.set(i, j, get(i, j));
			}
		}

		return subMatrix;
	}

	/**
	 * Returns a submatrix of this matrix, excluding the specified row and column.
	 * <p>
	 * This method creates a new matrix by excluding the specified row and column
	 * from the original matrix. The resulting submatrix will have dimensions one
	 * less in both rows and columns than the original matrix.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double> subMatrix = matrix.getSubMatrix(1, 2); // Excludes row 1 and column 2
	 * </pre>
	 * </p>
	 *
	 * @param i the index of the row to exclude
	 * @param j the index of the column to exclude
	 * @return the submatrix of this matrix, excluding the specified row and column
	 */
	public Matrix<T> getSubMatrix(int i, int j) {
		// Create a new matrix with one less row and one less column
		Matrix<T> subMatrix = new Matrix<>(nrows - 1, ncols - 1);

		// Copy the elements from this matrix to the submatrix, excluding the specified
		// row and column
		int rowIndex = 0;
		for (int k = 0; k < nrows; k++) {
			if (k == i) {
				// Skip the specified row
				continue;
			}
			int colIndex = 0;
			for (int l = 0; l < ncols; l++) {
				if (l == j) {
					// Skip the specified column
					continue;
				}
				subMatrix.set(rowIndex, colIndex, get(k, l));
				colIndex++;
			}
			rowIndex++;
		}

		return subMatrix;
	}

	/**
	 * Extracts a subMatrix from the original matrix, using specified start and end
	 * indices for rows and columns.
	 * <p>
	 * This method allows for the extraction of a subMatrix from the original matrix
	 * by specifying the start and end indices for both rows and columns. The
	 * indices are 1-based, meaning the first row or column is indexed as 1.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Double> matrix = new Matrix<>(3, 3);
	 * // Assume matrix is initialized with Double values
	 * Matrix<Double> subMatrix = matrix.getSubMatrix(1, 3, 1, 3); // Extracts a 2x2 subMatrix from the 1st to 3rd row
	 * 															// and column
	 * </pre>
	 * </p>
	 *
	 * @param rowstart The start index for the row (inclusive).
	 * @param rowend   The end index for the row (exclusive).
	 * @param colstart The start index for the column (inclusive).
	 * @param colend   The end index for the column (exclusive).
	 * @return A new matrix object that is a subMatrix of the current matrix.
	 * @throws IllegalArgumentException if the indices are invalid.
	 */
	public Matrix<T> getSubMatrix(int rowstart, int rowend, int colstart, int colend) {
		// Check if the indices are valid
		if (rowstart < 1 || rowend > nrows || colstart < 1 || colend > ncols || rowstart > rowend
				|| colstart > colend) {
			throw new IllegalArgumentException("Invalid indices for subMatrix!");
		}

		// Create a new Matrix to hold the subMatrix
		Matrix<T> subMatrix = new Matrix<>(rowend - rowstart + 1, colend - colstart + 1);

		// Extract the subMatrix
		for (int i = rowstart; i <= rowend; i++) {
			for (int j = colstart; j <= colend; j++) {
				T value = this.array[(i - 1) * ncols + (j - 1)];
				subMatrix.array[(i - rowstart) * subMatrix.ncols + (j - colstart)] = value;
			}
		}

		return subMatrix;
	}

	/**
	 * Method. Check matrix convergence (Jacobi eigenvalues) using off-diagonal
	 * element norm as tolerance (e.g., 1e-10). Choose tolerance carefully: too low
	 * = slow/no convergence, too high = inaccurate results. Experiment for optimal
	 * balance. (299 chars)
	 *
	 * @param M         The matrix to check for convergence.
	 * @param tolerance The tolerance within which the norm of the off-diagonal
	 *                  elements should fall for convergence.
	 * @return true if the norm of the off-diagonal elements is less than the
	 *         tolerance, false otherwise.
	 */
	/**
	 * Checks matrix convergence using off-diagonal element norm as tolerance.
	 * <p>
	 * This method evaluates the convergence of the matrix based on the norm of its
	 * off-diagonal elements. Convergence is determined if the norm of the
	 * off-diagonal elements falls below a specified tolerance. It is based on the
	 * Jacobi eigenvalues. Choose tolerance carefully: too low => slow/no
	 * convergence, too high => inaccurate results. Experiment for optimal balance
	 * </p>
	 *
	 * @param M         The matrix to check for convergence.
	 * @param tolerance The tolerance within which the norm of the off-diagonal
	 *                  elements should fall for convergence.
	 * @return true if the norm of the off-diagonal elements is less than the
	 *         tolerance, false otherwise.
	 */
	public boolean converged(Number[][] M, double tolerance) {
		// Initialize the norm of the off-diagonal elements
		double offDiagonalNorm = 0;

		// Loop over the Matrix
		for (int i = 0; i < M.length; i++) {
			for (int j = 0; j < M.length; j++) {
				// Exclude diagonal elements
				if (i != j) {
					// Add the square of the current off-diagonal element to the norm
					offDiagonalNorm += M[i][j].doubleValue() * M[i][j].doubleValue();
				}
			}
		}

		// Take the square root of the sum to finalize the computation of the norm
		offDiagonalNorm = Math.sqrt(offDiagonalNorm);

		// Check if the norm is less than the tolerance
		return offDiagonalNorm < tolerance;
	}

	/**
	 * Calculates the Singular Value Decomposition (SVD) of the matrix using
	 * Householder QR decomposition.
	 * <p>
	 * This method decomposes the matrix into three matrices U, Σ, and V^T, where U
	 * and V are orthogonal matrices and Σ is a diagonal matrix containing the
	 * singular values of the original matrix. The decomposition is performed using
	 * the Householder QR method.
	 * </p>
	 *
	 * @return An array containing the U, Σ, and V^T matrices of the SVD.
	 * @throws RuntimeException if the matrix is not square.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T>[] singularValueDecompositionWithHouseholderQR() throws RuntimeException {
		if (nrows != ncols) {
			throw new RuntimeException("Matrix must be square.");
		}
		// Initialize U and V as identity matrices
		Matrix<T> U = identity(nrows, ncols);
		Matrix<T> V = identity(nrows, ncols);

		// Perform QR decomposition iteratively
		for (int i = 0; i < nrows - 1; i++) {
			// Perform QR decomposition on the subMatrix from the i-th row and column
			// to the end
			Matrix<T> subMatrix = getSubMatrix(i, nrows - 1, i, ncols - 1);
			Matrix<T>[] qrMatrices = subMatrix.qrDecompositionHouseholder();
			Matrix<T> Q = qrMatrices[0];
			Matrix<T> R = qrMatrices[1];

			// Update U and V
			U.set(i, nrows - 1, i, ncols - 1, Q);
			V.set(i, nrows - 1, i, ncols - 1, Q.transpose());

			// Update the original Matrix
			set(i, nrows - 1, i, ncols - 1, R);
		}

		// The singular values are the diagonal elements of the original Matrix
		// after the transformations
		Matrix<T> S = new Matrix<>(this.diag(), 1);

		return new Matrix[] { U, S, V.transpose() };
	}

	/**
	 * Calculates the Singular Value Decomposition (SVD) of the matrix using
	 * Gram-Schmidt QR decomposition.
	 * <p>
	 * This method decomposes the matrix into three matrices U, Σ, and V^T, where U
	 * and V are orthogonal matrices and Σ is a diagonal matrix containing the
	 * singular values of the original matrix. The decomposition is performed using
	 * the Gram-Schmidt QR method.
	 * </p>
	 *
	 * @return An array containing the U, Σ, and V^T matrices of the SVD.
	 * @throws RuntimeException if the matrix is not square.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T>[] singularValueDecompositionWithGramSchmidtQR() throws RuntimeException {
		if (nrows != ncols) {
			throw new RuntimeException("Matrix must be square.");
		}
		// Initialize U and V as identity matrices
		Matrix<T> U = identity(nrows, ncols);
		Matrix<T> V = identity(nrows, ncols);

		// Perform QR decomposition iteratively
		for (int i = 0; i < nrows - 1; i++) {
			// Perform QR decomposition on the subMatrix from the i-th row and column
			// to the end
			Matrix<T> subMatrix = getSubMatrix(i, nrows - 1, i, ncols - 1);
			Matrix<T>[] qrMatrices = subMatrix.qrDecompositionGramSchmidt();
			Matrix<T> Q = qrMatrices[0];
			Matrix<T> R = qrMatrices[1];

			// Update U and V
			U.set(i, nrows - 1, i, ncols - 1, Q);
			V.set(i, nrows - 1, i, ncols - 1, Q.transpose());

			// Update the original Matrix
			set(i, nrows - 1, i, ncols - 1, R);
		}

		// The singular values are the diagonal elements of the original Matrix
		// after the transformations
		Matrix<T> S = new Matrix<>(this.diag(), 1);

		return (Matrix<T>[]) new Matrix[] { U, S, V.transpose() };
	}

	/**
	 * Calculates the Singular Value Decomposition (SVD) of the matrix using Givens
	 * QR decomposition.
	 * <p>
	 * This method decomposes the matrix into three matrices U, Σ, and V^T, where U
	 * and V are orthogonal matrices and Σ is a diagonal matrix containing the
	 * singular values of the original matrix. The decomposition is performed using
	 * the Givens QR method.
	 * </p>
	 *
	 * @return An array containing the U, Σ, and V^T matrices of the SVD.
	 * @throws RuntimeException if the matrix is not square.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T>[] singularValueDecompositionWithGivensQR() throws RuntimeException {
		if (nrows != ncols) {
			throw new RuntimeException("matrix must be square.");
		}
		// Initialize U and V as identity matrices
		Matrix<T> U = identity(nrows, ncols);
		Matrix<T> V = identity(nrows, ncols);

		// Perform QR decomposition iteratively
		for (int i = 0; i < nrows - 1; i++) {
			// Perform QR decomposition on the subMatrix from the i-th row and column
			// to the end
			Matrix<T> subMatrix = getSubMatrix(i, nrows - 1, i, ncols - 1);
			Matrix<T>[] qrMatrices = subMatrix.qrDecompositionGivens();
			Matrix<T> Q = qrMatrices[0];
			Matrix<T> R = qrMatrices[1];

			// Update U and V
			U.set(i, nrows - 1, i, ncols - 1, Q);
			V.set(i, nrows - 1, i, ncols - 1, Q.transpose());

			// Update the original Matrix
			set(i, nrows - 1, i, ncols - 1, R);
		}

		// The singular values are the diagonal elements of the original Matrix
		// after the transformations
		Matrix<T> S = new Matrix<>(this.diag(), 1);

		return (Matrix<T>[]) new Matrix[] { U, S, V.transpose() };
	}

	/**
	 * Method. Calculate the singular values of a matrix.
	 * 
	 * @return The singular values of the matrix.
	 * @throws RuntimeException if the matrix is not square.
	 */
	public double[] singularValues() throws RuntimeException {
		if (nrows != ncols) {
			throw new RuntimeException("Matrix must be square.");
		}
		// Perform SVD decomposition
		Matrix<T>[] svdMatrices = this.singularValueDecompositionWithHouseholderQR();
		Matrix<T> S = svdMatrices[1];

		// Extract the singular values from the diagonal of S
		double[] singularValues = new double[nrows];
		for (int i = 0; i < nrows; i++) {
			singularValues[i] = S.get(i, i).doubleValue();
		}

		return singularValues;

	}

	/**
	 * Calculates the singular values of a matrix using a specified decomposition
	 * method.
	 * <p>
	 * This method calculates the singular values of the matrix by performing a
	 * Singular Value Decomposition (SVD) using the specified decomposition method.
	 * The supported methods include "Householder", "GramSchmidt", and "Givens".
	 * </p>
	 *
	 * @param decompositionMethodName The name of the decomposition method to use.
	 * @return An array of double values representing the singular values of the
	 *         matrix.
	 * @throws RuntimeException if the matrix is not square or if an invalid
	 *                          decomposition method name is provided.
	 */
	public double[] singularValues(String decompositionMethodName) throws RuntimeException {
		if (nrows != ncols) {
			throw new RuntimeException("Matrix must be square.");
		}
		Matrix<T>[] svdMatrices;
		if (decompositionMethodName.equalsIgnoreCase("Householder")) {
			// Perform SVD decomposition using Householder QR
			svdMatrices = this.singularValueDecompositionWithHouseholderQR();
		} else if (decompositionMethodName.equalsIgnoreCase("GramSchmidt")) {
			// Perform SVD decomposition using Gram-Schmidt QR
			svdMatrices = this.singularValueDecompositionWithGramSchmidtQR();
		} else if (decompositionMethodName.equalsIgnoreCase("Givens")) {
			// Perform SVD decomposition using Givens QR
			svdMatrices = this.singularValueDecompositionWithGivensQR();
		} else {
			throw new IllegalArgumentException("Invalid decomposition method name.");
		}

		Matrix<T> S = svdMatrices[1];

		// Extract the singular values from the diagonal of S
		double[] singularValues = new double[nrows];
		for (int i = 0; i < nrows; i++) {
			singularValues[i] = S.get(i, i).doubleValue();
		}

		return singularValues;
	}

	/**
	 * Calculates the adjugate of the matrix. The adjugate of a matrix is the
	 * transpose of the cofactor matrix.
	 * <p>
	 * This method computes the adjugate by calculating the cofactor for each
	 * element, applying the appropriate sign, and then transposing the resulting
	 * matrix. The adjugate is defined for square matrices only.
	 * </p>
	 *
	 * @return The adjugate Matrix.
	 * @throws IllegalStateException if the matrix is not square.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T> adjugate() {
		if (!isSquare()) {
			throw new IllegalStateException("Matrix must be square to compute the adjugate.");
		}

		Matrix<T> adjugate = new Matrix<>(ncols, nrows);
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				// Calculate the cofactor
				Matrix<T> subMatrix = this.minor(i, j);
				double cofactor = subMatrix.determinant();

				// Apply the sign based on the position
				double sign = ((i + j) % 2 == 0) ? 1 : -1;

				// Multiply cofactor by sign and cast to T
				T cofactorValue = (T) Double.valueOf(cofactor * sign);

				// Assign the cofactor with the appropriate sign to the adjugate Matrix
				// Note: The indices are swapped for transposition
				adjugate.set(j, i, cofactorValue);
			}
		}
		return adjugate;
	}

	/**
	 * Raises the matrix to the power of an exponent.
	 * <p>
	 * This method calculates the matrix raised to a specified exponent. For square
	 * matrices, it supports both positive and negative exponents. The method first
	 * checks if the matrix is square, then calculates the identity matrix of the
	 * same size and raises it to the specified exponent by multiplying it by the
	 * original matrix.
	 * </p>
	 *
	 * @param exponent The exponent to which the matrix is raised.
	 * @return The matrix raised to the specified power.
	 * @throws UnsupportedOperationException if the matrix is not square.
	 */
	public Matrix<T> power(int exponent) throws UnsupportedOperationException {
		// Ensure the Matrix is square
		if (nrows != ncols) {
			throw new UnsupportedOperationException("Matrix must be square to be raised to a power.");
		}

		// Create an identity Matrix of the same size as the current
		// Matrix
		Matrix<T> result = this.eye(nrows);

		// If the exponent is 0, the resulting Matrix is the identity
		// Matrix
		if (exponent == 0) {
			return result;
		}

		// If the exponent is negative, calculate the inverse and set the exponent to
		// its absolute value
		if (exponent < 0) {
			result = result.inverse();
			exponent = -exponent;
		}

		// Multiply the Matrix by itself exponent times
		for (int i = 0; i < exponent; i++) {
			result = result.multiply(this);
		}

		// Return the resulting Matrix
		return result;
	}

	/**
	 * Solves equations by nonsingular systems and least squares.
	 * <p>
	 * This method solves a system of linear equations or performs a least squares
	 * approximation using LU decomposition. It first decomposes the matrix into
	 * lower and upper triangular matrices (L and U) and then solves the system by
	 * forward and backward substitution.
	 * </p>
	 *
	 * @param b The right-hand side of the equation.
	 * @return The solution matrix.
	 */
	public Matrix<T> solve(Matrix<T> b) {
		Matrix<T>[] LU = this.luDecomposition();
		Matrix<T> L = LU[0];
		Matrix<T> U = LU[1];
		Matrix<T> y = forwardSubstitution(L, b);
		Matrix<T> x = backSubstitution(U, y);
		return x;
	}

	/**
	 * Calculates the condition number of the matrix.
	 * <p>
	 * The condition number of a matrix is the ratio of the largest singular value
	 * to the smallest singular value. It provides a measure of how sensitive the
	 * solution of the system of equations is to small changes in the coefficients.
	 * </p>
	 *
	 * @return The condition number of the matrix.
	 */
	public double conditionNumber() {
		double[] singularValues = this.singularValues();
		double max = Arrays.stream(singularValues).max().getAsDouble();
		double min = Arrays.stream(singularValues).min().getAsDouble();
		return max / min;
	}

	/**
	 * Calculates the rank of the matrix.
	 * <p>
	 * The rank of a matrix is the maximum number of linearly independent rows (or
	 * columns). It can be found as the number of nonzero singular values. This
	 * method uses the singular values of the matrix to determine its rank.
	 * </p>
	 *
	 * @return The rank of the matrix.
	 */
	public int rank() {
		double tolerance = 1e-10; // tolerance
		double[] singularValues = this.singularValues();
		int rank = 0;
		for (double sv : singularValues) {
			if (sv > tolerance) {
				rank++;
			}
		}
		return rank;
	}

	/**
	 * Calculates the pseudoinverse of the matrix.
	 * <p>
	 * The pseudoinverse of a matrix A, denoted A⁺, is a generalization of the
	 * inverse matrix. It is defined and unique for all matrices whose entries are
	 * real or complex numbers. This method computes the pseudoinverse by performing
	 * a singular value decomposition (SVD) and then calculating the pseudoinverse
	 * of the singular values.
	 * </p>
	 *
	 * @return The pseudoinverse of the matrix.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T> pseudoinverse() {
		Matrix<T>[] svd = this.singularValueDecompositionWithHouseholderQR();
		Matrix<T> U = svd[0];
		Matrix<T> S = svd[1];
		Matrix<T> V = svd[2];

		// Compute the pseudoinverse of the singular values
		for (int i = 0; i < S.nrows; i++) {
			if (S.get(i, i).doubleValue() != 0) {
				T pseudoInverseValue = (T) Double.valueOf(1.0 / S.get(i, i).doubleValue());
				S.set(i, i, pseudoInverseValue);
			}
		}

		// Compute the pseudoinverse of the Matrix
		Matrix<T> pseudoinverse = V.multiply(S.transpose()).multiply(U.transpose());
		return pseudoinverse;
	}

	/**
	 * Returns the conjugate of this matrix.
	 * <p>
	 * The conjugate of a matrix is obtained by taking the complex conjugate of each
	 * element. For matrices with real elements, the conjugate matrix is identical
	 * to the original matrix. For complex matrices, this method applies the complex
	 * conjugate operation to each element, resulting in a new matrix where each
	 * complex number is replaced by its complex conjugate.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix<Complex> matrix = new Matrix<>(2, 2);
	 * // Assume matrix is initialized with Complex values
	 * Matrix<Complex> conjugateMatrix = matrix.conjugate();
	 * </pre>
	 * </p>
	 *
	 * @return The conjugate of this matrix.
	 */
	public Matrix<T> conjugate() {
		// Create a new matrix with the same dimensions as this matrix
		Matrix<T> conjugateMatrix = new Matrix<>(nrows, ncols);

		// Copy the elements from this matrix to the conjugate matrix, conjugating each
		// element
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				conjugateMatrix.set(i, j, getSubMatrix(i, j).conjugate());
			}
		}

		return conjugateMatrix;
	}

	/**
	 * Returns the conjugate transpose of the matrix.
	 * <p>
	 * The conjugate transpose of a matrix is obtained by taking the transpose and
	 * then taking the complex conjugate of each element. For real matrices, the
	 * transpose and conjugate transpose are the same.
	 * </p>
	 *
	 * @return The conjugate transpose of the matrix.
	 */
	public Matrix<T> getConjugateTranspose() {
		Matrix<T> transpose = new Matrix<>(ncols, nrows);

		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				T element = (T) to2DArray()[i][j];
				// For complex matrices, you would take the complex conjugate here.
				// For real matrices, this step is not needed.
				transpose.set(j, i, element);
			}
		}

		return transpose;
	}

	/************************************************
	 * STRASSEN-LIKE Matrix OPERATIONS
	 **********************************************/

	/**
	 * Performs Strassen's matrix multiplication algorithm.
	 * <p>
	 * This method implements Strassen's algorithm for matrix multiplication, which is a divide-and-conquer
	 * algorithm that recursively breaks down the input matrices into smaller sub-matrices and combines
	 * the results to obtain the product. This method is efficient for large matrices and takes advantage
	 * of the principle of parallelism in the computation.
	 * </p>
	 * <p>
	 * Example usage:
	 * <pre>
	 *     Matrix<Integer> A = new Matrix<>(3, 3);
	 *     Matrix<Integer> B = new Matrix<>(3, 3);
	 *     // Assume A and B are initialized with values
	 *     Matrix<Integer> C = A.strassen(B);
	 * </pre>
	 * </p>
	 *
	 * @param B The matrix to multiply with the current matrix.
	 * @return The product of the current matrix and matrix B, computed using Strassen's algorithm.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T> strassen(Matrix<T> B) {
		int n = nrows;
		T[][] A = to2DArray();
		T[][] Barray = B.to2DArray();
		T[][] C = (T[][]) new Number[n][n];
		if (n <= 32) {
			return this.multiply(B);
		}
		int m = n / 2;
		T[][] A11 = (T[][]) new Number[m][m];
		T[][] A12 = (T[][]) new Number[m][m];
		T[][] A21 = (T[][]) new Number[m][m];
		T[][] A22 = (T[][]) new Number[m][m];
		T[][] B11 = (T[][]) new Number[m][m];
		T[][] B12 = (T[][]) new Number[m][m];
		T[][] B21 = (T[][]) new Number[m][m];
		T[][] B22 = (T[][]) new Number[m][m];
		Matrix<T> A1 = new Matrix<>(A11);
		Matrix<T> A2 = new Matrix<>(A12);
		Matrix<T> A3 = new Matrix<>(A21);
		Matrix<T> A4 = new Matrix<>(A22);
		Matrix<T> B1 = new Matrix<>(B11);
		Matrix<T> B2 = new Matrix<>(B12);
		Matrix<T> B3 = new Matrix<>(B21);
		Matrix<T> B4 = new Matrix<>(B22);
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {
				A11[i][j] = A[i][j];
				A12[i][j] = A[i][j + m];
				A21[i][j] = A[i + m][j];
				A22[i][j] = A[i + m][j + m];
				B11[i][j] = Barray[i][j];
				B12[i][j] = Barray[i][j + m];
				B21[i][j] = Barray[i + m][j];
				B22[i][j] = Barray[i + m][j + m];
			}
		}
		T[][] C11 = A1.strassen(B1).to2DArray();
//		T[][] C12 = A1.strassen(B2).to2DArray();
		T[][] C21 = A3.strassen(B1).to2DArray();
		T[][] C22 = A4.strassen(B4).to2DArray();
		T[][] C31 = A2.strassen(B2).to2DArray();
		T[][] C32 = A4.strassen(B3).to2DArray();
		T[][] C41 = A1.strassen(B4).to2DArray();
//		T[][] C42 = A3.strassen(B2).to2DArray();
		T[][] C51 = A2.strassen(B3).to2DArray();
//		T[][] C52 = A4.strassen(B4).to2DArray();
//		T[][] C61 = A1.strassen(B2).to2DArray();
		T[][] C62 = A3.strassen(B4).to2DArray();
		T[][] C71 = A2.strassen(B1).to2DArray();
//		T[][] C72 = A4.strassen(B1).to2DArray();

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {
				C[i][j] = subtract(add(C11[i][j], C41[i][j]), add(C51[i][j], C71[i][j]));
				C[i][j + m] = add(C31[i][j], C51[i][j]);
				C[i + m][j] = add(C21[i][j], C41[i][j]);
				C[i + m][j + m] = subtract(add(C11[i][j], C22[i][j]), add(C32[i][j], C62[i][j]));
			}
		}
		return new Matrix<>(C);
	}

	/**
	 * Performs Strassen's matrix multiplication algorithm on two matrices.
	 * <p>
	 * This method implements a generalization of Strassen's algorithm for matrix multiplication, taking two
	 * matrices as input and returning their product. The algorithm is optimized for large matrices by
	 * partitioning them into smaller sub-matrices and performing the multiplication in a divide-and-conquer
	 * fashion. This method ensures that the matrix multiplication is performed efficiently, leveraging
	 * the parallelism inherent in Strassen's algorithm.
	 * </p>
	 * <p>
	 * Example usage:
	 * <pre>
	 *     Matrix<Integer> A = new Matrix<>(3, 3);
	 *     Matrix<Integer> B = new Matrix<>(3, 3);
	 *     // Assume A and B are initialized with values
	 *     Matrix<Integer> C = Matrix.strassenMultiply(A, B);
	 * </pre>
	 * </p>
	 *
	 * @param A The first matrix to multiply.
	 * @param B The second matrix to multiply.
	 * @return The product of matrices A and B, computed using Strassen's algorithm.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T> strassenMultiply(Matrix<T> A, Matrix<T> B) {
		int n = nrows;
		if (n == 1) {
			// Ensure this.multiply(B) is correctly implemented for 1x1 matrices
			return A.multiply(B);
		} else {
			// Ensure partition is correctly implemented
			Matrix<T>[][] partitionedThis = partition(A);
			Matrix<T>[][] partitionedB = partition(B);

			Matrix<T> P1 = strassenMultiply(partitionedThis[0][0].plus(partitionedThis[1][1]),
					partitionedB[0][0].plus(partitionedB[1][1]));
			Matrix<T> P2 = strassenMultiply(partitionedThis[1][0].plus(partitionedThis[1][1]), partitionedB[0][0]);
			Matrix<T> P3 = strassenMultiply(partitionedThis[0][0], partitionedB[0][1].minus(partitionedB[1][1]));
			Matrix<T> P4 = strassenMultiply(partitionedThis[1][1], partitionedB[1][0].minus(partitionedB[0][0]));
			Matrix<T> P5 = strassenMultiply(partitionedThis[0][0].plus(partitionedThis[0][1]), partitionedB[1][1]);
			Matrix<T> P6 = strassenMultiply(partitionedThis[1][0].minus(partitionedThis[0][0]),
					partitionedB[0][0].plus(partitionedB[0][1]));
			Matrix<T> P7 = strassenMultiply(partitionedThis[0][1].minus(partitionedThis[1][1]),
					partitionedB[1][0].plus(partitionedB[1][1]));

			Matrix<T> C11 = P1.plus(P4).minus(P5).plus(P7);
			Matrix<T> C12 = P3.plus(P5);
			Matrix<T> C21 = P2.plus(P4);
			Matrix<T> C22 = P1.plus(P3).minus(P2).plus(P6);

			// Ensure concatenateHorizontally is correctly implemented
			Matrix<T> C = concatenateHorizontally(C11, C12, C21, C22);
			return C;
		}
	}

	/**
	 * Partitions the given matrix into four equal-sized sub-matrices.
	 * <p>
	 * This method divides the input matrix into four sub-matrices of equal size, assuming the matrix is
	 * square and its dimensions are evenly divisible by 2. The method returns a 2D array containing
	 * the four sub-matrices, arranged in a 2x2 grid. This partitioning is a crucial step in the
	 * Strassen algorithm, as it allows for the recursive application of the algorithm to the sub-matrices.
	 * </p>
	 * <p>
	 * Example usage:
	 * <pre>
	 *     Matrix<Integer> B = new Matrix<>(4, 4);
	 *     // Assume B is initialized with values
	 *     Matrix<Integer>[][] partitions = B.partition();
	 * </pre>
	 * </p>
	 *
	 * @param B The matrix to partition.
	 * @return A 2D array containing the four sub-matrices of B.
	 */
	@SuppressWarnings("unchecked")
	public Matrix<T>[][] partition(Matrix<T> B) {
		int midRow = B.getNrows() / 2;
		int midCol = B.getNcols() / 2;

		Matrix<T> topLeft = B.getSubMatrix(0, midRow - 1, 0, midCol - 1);
		Matrix<T> topRight = B.getSubMatrix(0, midRow - 1, midCol, B.getNcols() - 1);
		Matrix<T> bottomLeft = B.getSubMatrix(midRow, B.getNrows() - 1, 0, midCol - 1);
		Matrix<T> bottomRight = B.getSubMatrix(midRow, B.getNrows() - 1, midCol, B.getNcols() - 1);

		return new Matrix[][] { { topLeft, topRight }, { bottomLeft, bottomRight } };
	}

	/**
	 * Partitions this matrix into four equal-sized sub-matrices.
	 * <p>
	 * This method is a convenience method that partitions the current matrix into four equal-sized
	 * sub-matrices. It assumes that the matrix is square and its dimensions are evenly divisible by 2.
	 * The method returns a 2D array containing the four sub-matrices, arranged in a 2x2 grid. This
	 * partitioning is a crucial step in the Strassen algorithm, as it allows for the recursive
	 * application of the algorithm to the sub-matrices.
	 * </p>
	 *
	 * @return A 2D array containing the four sub-matrices of the current matrix.
	 */
	public Matrix<T>[][] partition() {
		return partition(this);
	}

	/*******************************************
	 * MISCELLANOUS METHODS
	 *****************************************/

	/**
	 * Method. Check if this matrix is positive definite.
	 *
	 * A matrix is positive definite if it satisfies the following conditions: 1.
	 * The matrix is square (i.e. has the same number of rows and columns) 2. The
	 * matrix is symmetric (i.e. equal to its own transpose) 3. All the eigenvalues
	 * of the matrix are positive
	 *
	 * @return true if the matrix is positive definite, false otherwise
	 */
	public boolean isPositiveDefinite() {
		// Check if the matrix is square
		if (nrows != ncols) {
			return false;
		}

		// Check if the matrix is symmetric
		if (!equals(transpose())) {
			return false;
		}

		// Calculate the eigenvalues of the matrix
		T[] eigenvalues = eigenvalues();

		// Check if all the eigenvalues are positive
		for (T eigenvalue : eigenvalues) {
			if (eigenvalue.doubleValue() <= 0) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Method. Check if this matrix satisfies the metric property.
	 *
	 * A matrix satisfies the metric property if it is positive definite and all its
	 * diagonal elements are equal to 1.
	 *
	 * @return true if the matrix satisfies the metric property, false otherwise
	 */
	public boolean isMetricPositiveDefined() {
		// Check if the matrix is positive definite
		if (!isPositiveDefinite()) {
			return false;
		}

		// Check if all the diagonal elements are equal to 1
		for (int i = 0; i < nrows; i++) {
			if (!get(i, i).equals(oneValue())) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Method. Return true if the matrix is diagonal, false otherwise.
	 *
	 * A matrix is diagonal if all non-diagonal elements are zero.
	 *
	 * @return true if the matrix is diagonal, false otherwise
	 */
	public boolean isDiagonal2() {
		if (nrows != ncols) {
			return false;
		}
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				if (i != j && !array[i * ncols + j].equals(0)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Method. Return true if the matrix is an identity matrix, false otherwise.
	 *
	 * An identity matrix is a square matrix with ones on the main diagonal and
	 * zeros elsewhere.
	 *
	 * @return true if the matrix is an identity matrix, false otherwise
	 */
	public boolean isIdentity2() {
		if (nrows != ncols) {
			return false;
		}
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				if (i == j && !array[i * ncols + j].equals(1)) {
					return false;
				} else if (i != j && !array[i * ncols + j].equals(0)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Method. Return true if the matrix is orthogonal, false otherwise.
	 *
	 * A matrix is orthogonal if its transpose is equal to its inverse.
	 *
	 * @return true if the matrix is orthogonal, false otherwise
	 */
	public boolean isOrthogonal2() {
		if (nrows != ncols) {
			return false;
		}
		Matrix<T> transpose = transpose();
		Matrix<T> inverse = transpose.inverse();
		return this.equals(inverse);
	}

	/**
	 * Method. Return true if the matrix is upper triangular, false otherwise.
	 *
	 * An upper triangular matrix is a matrix where all elements below the main
	 * diagonal are zero.
	 *
	 * @return true if the matrix is upper triangular, false otherwise
	 */
	public boolean isUpperTriangular2() {
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < i; j++) {
				if (!array[i * ncols + j].equals(0)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Method. Return true if the matrix is lower triangular, false otherwise.
	 *
	 * A lower triangular matrix is a matrix where all elements above the main
	 * diagonal are zero.
	 *
	 * @return true if the matrix is lower triangular, false otherwise
	 */
	public boolean isLowerTriangular2() {
		for (int i = 0; i < nrows; i++) {
			for (int j = i + 1; j < ncols; j++) {
				if (!array[i * ncols + j].equals(0)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Method. Check if the matrix is empty or not.
	 *
	 * @return true if the matrix is empty, false otherwise
	 */
	public boolean isEmpty() {
		return nrows == 0 || ncols == 0;
	}

	/**
	 * Method. Check if the matrix is diagonal or not.
	 *
	 * @return true if the matrix is diagonal, false otherwise
	 */
	public boolean isDiagonal() {
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				if (i != j && !get(i, j).equals(0)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Method. Check if the matrix is identity or not.
	 *
	 * @return true if the matrix is identity, false otherwise
	 */
	public boolean isIdentity() {
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				if (i == j && !get(i, j).equals(1)) {
					return false;
				} else if (i != j && !get(i, j).equals(0)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Method. Check if the matrix is orthogonal or not.
	 *
	 * @return true if the matrix is orthogonal, false otherwise
	 */
	public boolean isOrthogonal() {
		Matrix<T> transpose = this.transpose();
		return this.multiply(transpose).isIdentity();
	}

	/**
	 * Method. Check if the matrix is upper triangular or not.
	 *
	 * @return true if the matrix is upper triangular, false otherwise
	 */
	public boolean isUpperTriangular() {
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < i; j++) {
				if (!get(i, j).equals(0)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Method. Check if the matrix is lower triangular or not.
	 *
	 * @return true if the matrix is lower triangular, false otherwise
	 */
	public boolean isLowerTriangular() {
		for (int i = 0; i < nrows; i++) {
			for (int j = i + 1; j < ncols; j++) {
				if (!get(i, j).equals(0)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Method. Check if the matrix is symmetric or not.
	 *
	 * @return true if the matrix is symmetric, false otherwise
	 */
	public boolean isSymmetric() {
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				if (!get(i, j).equals(get(j, i))) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Checks if the matrix is symmetric and positive definite.
	 * <p>
	 * This method verifies whether the matrix is both symmetric and positive
	 * definite. A matrix is symmetric if it is equal to its transpose, and positive
	 * definite if all its principal minors are positive.
	 * </p>
	 *
	 * @return true if the matrix is symmetric and positive definite, false
	 *         otherwise.
	 */
	public boolean isSymmetricPositiveDefined() {
		// Check if the Matrix is square
		if (nrows != ncols) {
			return false;
		}

		// Check if the Matrix is symmetric
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < i; j++) {
				if (!array[i * ncols + j].equals(array[j * ncols + i])) {
					return false;
				}
			}
		}

		// Check if the Matrix is positive definite
		for (int i = 0; i < nrows; i++) {
			Matrix<T> subMatrix = getSubMatrix(i);
			if (subMatrix.determinant() <= 0) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Method. Check if the matrix is Hermitian or not.
	 *
	 * @return true if the matrix is Hermitian, false otherwise
	 */
	@SuppressWarnings("unlikely-arg-type")
	public boolean isHermitian() {
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				if (!get(i, j).equals(getSubMatrix(j, i).conjugate())) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Method. Check if the matrix is square or not.
	 *
	 * @return true if the matrix is square, false otherwise
	 */
	public boolean isSquare() {
		return nrows == ncols;
	}

	/**
	 * Method. Return true if the matrix is nonsingular, false otherwise. A matrix
	 * is nonsingular if its determinant is not zero.
	 */
	public boolean isNonsingular() {
		return Math.abs(determinant()) > 0;
	}

	/**
	 * Method. Return true if the matrix is singular, false otherwise. A matrix is
	 * singular if its determinant is zero.
	 * 
	 * <p>
	 * A matrix is singular if it does not have an inverse, meaning its determinant
	 * is zero. Singular matrices do not have a multiplicative inverse and cannot be
	 * used to solve systems of linear equations.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix&lt;Double&gt; matrix = new Matrix&lt;&gt;(3, 3);
	 * // Assume matrix is initialized with values
	 * boolean isSingular = matrix.isSingular();
	 * if (isSingular) {
	 * 	System.out.println("The matrix is singular.");
	 * } else {
	 * 	System.out.println("The matrix is regular.");
	 * }
	 * </pre>
	 * </p>
	 *
	 * @return {@code true} if the matrix is singular, {@code false} otherwise.
	 */
	public boolean isSingular() {
		return Math.abs(determinant()) == 0;
	}

	/**
	 * Method .Return true if the matrix is full rank, false otherwise. A matrix is
	 * full rank if its rank is equal to the minimum of its number of rows and
	 * columns.
	 */
	public boolean isFullRank() {
		int rank = Math.min(nrows, ncols);
		for (int i = 0; i < rank; i++) {
			if (Math.abs(getSubMatrix(i, i).determinant()) == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Method. Check if the columns of this matrix span only the column space.
	 *
	 * @return true if the columns span only the column space, false otherwise
	 */
	public boolean isColumnSpaceOnly() {
		// A matrix spans only the column space if it has full column rank
		return rank() == ncols;
	}

	/**
	 * Method. Check if the rows of this matrix span only the row space.
	 *
	 * @return true if the rows span only the row space, false otherwise
	 */
	public boolean isRowSpaceOnly() {
		// A matrix spans only the row space if it has full row rank
		return rank() == nrows;
	}

	/**
	 * Method. Check if the vectors in this matrix are linearly dependent.
	 *
	 * @return true if the vectors are linearly dependent, false otherwise
	 */
	public boolean isLinearlyDependent() {
		// A matrix is linearly dependent if its determinant is 0
		return determinant() == 0;
	}

	/**
	 * Method. Check if the vectors in this matrix are linearly independent.
	 *
	 * @return true if the vectors are linearly independent, false otherwise
	 */
	public boolean isLinearlyIndependent() {
		// A matrix is linearly independent if its determinant is not 0
		return determinant() != 0;
	}

	/**
	 * Method. Checks if the matrix is normal.
	 * <p>
	 * A matrix is considered normal if it commutes with its conjugate transpose. In
	 * other words, for a matrix A, A must be equal to its conjugate transpose A*
	 * when multiplied by A and A* by A.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix&lt;Double&gt; matrix = new Matrix&lt;&gt;(3, 3);
	 * // Assume matrix is initialized with values
	 * boolean isNormal = matrix.isNormal();
	 * if (isNormal) {
	 * 	System.out.println("The matrix is normal.");
	 * } else {
	 * 	System.out.println("The matrix is not normal.");
	 * }
	 * </pre>
	 * </p>
	 *
	 * @return {@code true} if the matrix is normal, {@code false} otherwise.
	 */
	public boolean isNormal() {
		// Assuming getConjugateTranspose() returns the conjugate transpose of the
		// matrix
		Matrix<T> conjugateTranspose = this.getConjugateTranspose();
		return this.times(conjugateTranspose).equals(conjugateTranspose.times(this));
	}

	/**
	 * Checks if the matrix is regular (non-singular).
	 * <p>
	 * A matrix is regular if it has an inverse, meaning its determinant is not
	 * zero. A regular matrix can be multiplied by its inverse to produce the
	 * identity matrix.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * Matrix&lt;Double&gt; matrix = new Matrix&lt;&gt;(3, 3);
	 * // Assume matrix is initialized with values
	 * boolean isRegular = matrix.isRegular();
	 * if (isRegular) {
	 * 	System.out.println("The matrix is regular.");
	 * } else {
	 * 	System.out.println("The matrix is singular.");
	 * }
	 * </pre>
	 * </p>
	 *
	 * @return {@code true} if the matrix is regular (non-singular), {@code false}
	 *         otherwise.
	 */
	public boolean isRegular() {
		// Assuming getDeterminant() returns the determinant of the matrix
		return this.determinant() != 0;
	}

	/************************************
	 * OTHER USEFUL METHODS
	 **********************************/

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < nrows; i++) {
			if (i > 0) {
				sb.append(", ");
			}
			sb.append("[");
			for (int j = 0; j < ncols; j++) {
				if (j > 0) {
					sb.append(", ");
				}
				sb.append(array[i * ncols + j]);
			}
			sb.append("]");
		}
		sb.append("]");
		return sb.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Matrix))
			return false;
		Matrix<?> Matrix = (Matrix<?>) o;
		return Arrays.equals(array, Matrix.array) && nrows == Matrix.nrows && ncols == Matrix.ncols;
	}

	/**
	 * Method. Print the matrix to the standard output in a formatted manner.
	 * <p>
	 * As a nice way to print a multidimensional array, This method converts the
	 * matrix to a 2D array and then formats the output string to make it more
	 * readable. It replaces occurrences of "], [" with a newline character to
	 * separate rows and removes unnecessary brackets and spaces to clean up the
	 * output.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * GenericMatrix&lt;Double&gt; matrix = new GenericMatrix&lt;&gt;(3, 3);
	 * // Assume matrix is initialized with values
	 * matrix.print();
	 * </pre>
	 * </p>
	 */
	public void print() {
		T[][] array2d = to2DArray();
		System.out.println(Arrays.deepToString(array2d).replace("], ", ",\n") // replace "], " by "\n" <----- there
																				// is a space into "], " to consider.
				.replace("[ ", "") // replace "[ " by "" <----- no space
				.replace("[", "") // replace "[" by "" <----- no space
				.replace("[[", "") // replace "[[" by "" <----- space
				.replace("]]", "") // replace "]]" by "" <----- no space
				.replace(",\n", "\n")); // replace ",\n" by "\n"
	}

	/**
	 * Method. Print the matrix in a style resembling MATLAB's matrix printing.
	 * <p>
	 * This method converts the matrix to a 2D array and formats the output string
	 * to mimic MATLAB's matrix printing style. It replaces occurrences of "], ["
	 * with "]\n" to separate rows and adjusts the brackets to closely match
	 * MATLAB's output format.
	 * </p>
	 * <p>
	 * Example usage:
	 * 
	 * <pre>
	 * GenericMatrix&lt;Double&gt; matrix = new GenericMatrix&lt;&gt;(3, 3);
	 * // Assume matrix is initialized with values
	 * matrix.printAsMatlab();
	 * </pre>
	 * </p>
	 */
	public void printAsMatlab() {
		T[][] array2d = to2DArray();
		System.out.println(Arrays.deepToString(array2d).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
	}

	/*************************************
	 * HELPER METHODS
	 **************************************/

	/**
	 * Method. Check if the dimensions of the Matrix are valid.
	 *
	 * @throws IllegalArgumentException if the number of rows or columns is not
	 *                                  positive.
	 */
	@SuppressWarnings("unused")
	private void checkDimensionsSign() {
		if (nrows <= 0 || ncols <= 0) {
			throw new IllegalArgumentException("Number of rows and columns must be positive.");
		}
	}

	/**
	 * Method. Check if the given dimensions are valid.
	 *
	 * @param nrows The number of rows to check.
	 * @param ncols The number of columns to check.
	 * @throws IllegalArgumentException if the number of rows or columns is not
	 *                                  positive.
	 */
	@SuppressWarnings("unused")
	private void checkDimensionsSign(int nrows, int ncols) {
		if (nrows <= 0 || ncols <= 0) {
			throw new IllegalArgumentException("Number of rows and columns must be positive.");
		}
	}

	/**
	 * Method. Check the dimensions of the right Matrix B by comparing them to that
	 * of the left. Useful for Addition and Subtraction of two matrices.
	 *
	 * @param B the right Matrix (the operand)
	 * @throws IllegalArgumentException if Matrix dimensions are not identical
	 */
	@SuppressWarnings("unused")
	private void checkDimensionsIdentity(Matrix<T> B) {
		if (this.nrows != B.nrows || this.ncols != B.ncols) {
			throw new IllegalArgumentException("Matrix dimensions must be identical!");
		}
	}

	/**
	 * Method. Check if the number of columns of Matrix A equals the number of rows
	 * of the Matrix B so that the operation C = AxB is possible
	 *
	 * @param B Operand
	 * @throws IllegalArgumentException if the number of columns of A is not equal
	 *                                  to the number of rows of B
	 */
	@SuppressWarnings("unused")
	private void checkSiColiLico(Matrix<T> B) { // si nombre de colonnes de A égale nombre de lignes de B, alors A*B
		// possible
		if (ncols != B.nrows) {
			throw new IllegalArgumentException(
					"Number of columns of A is not equal to the number of rows of B, so Matrix product is not possible.");
		}
	}

	/**
	 * Method. Compute Matrix-Matrix product (C=A*B), Vector-Vector product (C=v*v)
	 * or Matrix-vector product. The IKJ algorithm has better cache performance
	 * compared to other Matrix multiplication ijk-like algorithms, making it more
	 * efficient in practice.
	 *
	 * @param B left operand
	 * @return C the Matrix product ikj
	 */
	@SuppressWarnings("unchecked")
	private Matrix<T> ikjalgorithm1D(Matrix<T> B) {
		T[] A = this.getArray(); // A 1st Matrix (left operand) as a 1D array
		T[] Barray1D = B.getArray(); // B 2nd Matrix (right operand) as a 1D array

		// Transpose B if it is a row vector
		if (B.getNrows() == 1) {
			B = B.transpose().clone(); // Assuming transpose() returns a new matrix
		}

		int nrowsA = getNrows(); // number of rows of A
		int ncolsA = getNcols(); // number of columns of A
		int ncolsB = B.getNcols(); // number of columns of B

		// Check if multiplication is possible
		if (ncolsA != B.getNrows()) {
			throw new IllegalArgumentException("Number of columns in A must equal number of rows in B.");
		}

		// Initialize C with correct size
		T[] C = (T[]) new Number[nrowsA * ncolsB];

		// Perform the IKJ algorithm
		for (int i = 0; i < nrowsA; i++) {
			for (int k = 0; k < ncolsA; k++) {
				for (int j = 0; j < ncolsB; j++) {
					C[i * ncolsB + j] = (T) Double
							.valueOf(A[i * ncolsA + k].doubleValue() * Barray1D[k * ncolsB + j].doubleValue());
				}
			}
		}

		return new Matrix<>(nrowsA, ncolsB, C); // return in 1D form
	}

	@SuppressWarnings("unchecked")
	private T add(T a, T b) {
		if (a instanceof Integer) {
			return (T) Integer.valueOf(((Integer) a) + ((Integer) b));
		} else if (a instanceof Double) {
			return (T) Double.valueOf(((Double) a) + ((Double) b));
		} else if (a instanceof Float) {
			return (T) Float.valueOf(((Float) a) + ((Float) b));
		} else if (a instanceof Long) {
			return (T) Long.valueOf(((Long) a) + ((Long) b));
		}
		// Add more cases if needed for other numeric types
		throw new UnsupportedOperationException("Unsupported type for addition");
	}

	@SuppressWarnings("unchecked")
	private T subtract(T a, T b) {
		if (a instanceof Integer) {
			return (T) Integer.valueOf(((Integer) a) - ((Integer) b));
		} else if (a instanceof Double) {
			return (T) Double.valueOf(((Double) a) - ((Double) b));
		} else if (a instanceof Float) {
			return (T) Float.valueOf(((Float) a) - ((Float) b));
		} else if (a instanceof Long) {
			return (T) Long.valueOf(((Long) a) - ((Long) b));
		}
		// Add more cases if needed for other numeric types
		throw new UnsupportedOperationException("Unsupported type for subtraction");
	}

	/**
	 * Method. Negate a value.
	 *
	 * @param a The value to negate.
	 * @return The negated value.
	 * @throws UnsupportedOperationException if the type is not supported.
	 */
	@SuppressWarnings("unchecked")
	private T negate(T a) {
		if (a instanceof Integer) {
			return (T) Integer.valueOf(-((Integer) a));
		} else if (a instanceof Double) {
			return (T) Double.valueOf(-((Double) a));
		} else if (a instanceof Float) {
			return (T) Float.valueOf(-((Float) a));
		} else if (a instanceof Long) {
			return (T) Long.valueOf(-((Long) a));
		}
		// Add more cases if needed for other numeric types
		throw new UnsupportedOperationException("Unsupported type for negation");
	}

	@SuppressWarnings("unchecked")
	private T mult(T a, T b) {
		if (a instanceof Integer) {
			return (T) Integer.valueOf(((Integer) a) * ((Integer) b));
		} else if (a instanceof Double) {
			return (T) Double.valueOf(((Double) a) * ((Double) b));
		} else if (a instanceof Float) {
			return (T) Float.valueOf(((Float) a) * ((Float) b));
		} else if (a instanceof Long) {
			return (T) Long.valueOf(((Long) a) * ((Long) b));
		}
		// Add more cases if needed for other numeric types
		throw new UnsupportedOperationException("Unsupported type for multiplication");
	}

	@SuppressWarnings("unchecked")
	private T divide(T a, T b) {
		if (a instanceof Integer) {
			return (T) Integer.valueOf(((Integer) a) / ((Integer) b));
		} else if (a instanceof Double) {
			return (T) Double.valueOf(((Double) a) / ((Double) b));
		} else if (a instanceof Float) {
			return (T) Float.valueOf(((Float) a) / ((Float) b));
		} else if (a instanceof Long) {
			return (T) Long.valueOf(((Long) a) / ((Long) b));
		}
		// Add more cases if needed for other numeric types
		throw new UnsupportedOperationException("Unsupported type for division");
	}

	@SuppressWarnings("unchecked")
	private T zeroValue() {
		if (array instanceof Integer[]) {
			return (T) Integer.valueOf(0);
		} else if (array instanceof Double[]) {
			return (T) Double.valueOf(0.0);
		} else if (array instanceof Float[]) {
			return (T) Float.valueOf(0.0f);
		} else if (array instanceof Long[]) {
			return (T) Long.valueOf(0L);
		}
		// Add more cases if needed for other numeric types
		throw new UnsupportedOperationException("Unsupported type for zero value");
	}

	@SuppressWarnings("unchecked")
	private T oneValue() {
		if (array instanceof Integer[]) {
			return (T) Integer.valueOf(1);
		} else if (array instanceof Double[]) {
			return (T) Double.valueOf(1.0);
		} else if (array instanceof Float[]) {
			return (T) Float.valueOf(1.0f);
		} else if (array instanceof Long[]) {
			return (T) Long.valueOf(1L);
		}
		// Add more cases if needed for other numeric types
		throw new UnsupportedOperationException("Unsupported type for one value");
	}

	/**
	 * Returns the value 2 of the element type.
	 *
	 * @return The value 2 of the element type.
	 */
	@SuppressWarnings("unchecked")
	private T twoValue() {
		return (T) Double.valueOf(2.0);
	}

	/**
	 * Returns the sign of a number.
	 *
	 * @param x The number whose sign we want to compute.
	 * @return The sign of the number.
	 */
	@SuppressWarnings("unused")
	private int sign(T x) {
		if (x.doubleValue() > 0) {
			return 1;
		} else if (x.doubleValue() < 0) {
			return -1;
		} else {
			return 0;
		}
	}

	/**
	 * Method. Returns the square root of an element.
	 *
	 * @param x The element whose square root we want to compute.
	 * @return The square root of the element.
	 */
	private T sqrt(T x) {
		// Check if the element is non-negative
		if (x.doubleValue() < 0) {
			throw new IllegalArgumentException("Cannot take the square root of a negative number");
		}

		// Compute the square root using the Babylonian method
		T guess = divide(x, twoValue());
		T prevGuess = zeroValue();
		while (!guess.equals(prevGuess)) {
			prevGuess = guess;
			guess = divide(add(guess, divide(x, guess)), twoValue());
		}

		return guess;
	}

	/**
	 * Method. Calculate the square root of a value.
	 *
	 * @param a The value to calculate the square root of.
	 * @return The square root of the value.
	 * @throws UnsupportedOperationException if the type is not supported.
	 */
	@SuppressWarnings("unchecked")
	private T squareRoot(T a) {
		if (a instanceof Integer) {
			return (T) Integer.valueOf((int) Math.sqrt((Integer) a));
		} else if (a instanceof Double) {
			return (T) Double.valueOf(Math.sqrt((Double) a));
		} else if (a instanceof Float) {
			return (T) Float.valueOf((float) Math.sqrt((Float) a));
		} else if (a instanceof Long) {
			return (T) Long.valueOf((long) Math.sqrt((Long) a));
		}
		// Add more cases if needed for other numeric types
		throw new UnsupportedOperationException("Unsupported type for square root");
	}

}// class
