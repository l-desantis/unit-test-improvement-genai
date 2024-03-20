import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Existing class to be tested
class MyClass {
    int add(int a, int b) {
        return a + b;
    }
}

// Your extended test class (MyClassTest) to improve coverage
class MyClassTest {

    private MyClass myClass;

    @BeforeEach
    void setUp() {
        myClass = new MyClass();
    }

    @Test
    void testAddPositiveNumbers() {
        int result = myClass.add(5, 3);
        assertEquals(8, result, "Adding positive numbers should return the correct sum.");
    }

    @Test
    void testAddNegativeNumbers() {
        int result = myClass.add(-5, -3);
        assertEquals(-8, result, "Adding negative numbers should return the correct sum.");
    }

    @Test
    void testAddZero() {
        int result = myClass.add(0, 0);
        assertEquals(0, result, "Adding zero to zero should result in zero.");
    }

    @Test
    void testAddLargeNumbers() {
        // Example of adding large positive numbers
        int result = myClass.add(Integer.MAX_VALUE, 1);
        assertEquals(Integer.MIN_VALUE, result, "Adding large positive numbers should wrap around to negative.");
    }

    @Test
    void testAddNegativeAndPositive() {
        // Example of adding a negative and a positive number
        int result = myClass.add(-10, 20);
        assertEquals(10, result, "Adding negative and positive numbers should return the correct sum.");
    }

    // Add more test cases as needed

    // Example of an expected exception test
    @Test
    void testAddOverflow() {
        assertThrows(ArithmeticException.class, () -> myClass.add(Integer.MAX_VALUE, 1),
                "Adding large positive numbers should throw an ArithmeticException.");
    }
}
