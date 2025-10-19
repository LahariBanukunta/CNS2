//Week 8: Implement the Diffie-Hellman Key Exchange mechanism.
public class Main {

    // Modular exponentiation: (a^b) % p
    private static long power(long a, long b, long p) {
        long result = 1;
        a = a % p;

        while (b > 0) {
            if (b % 2 == 1)
                result = (result * a) % p;
            b = b / 2;
            a = (a * a) % p;
        }
        return result;
    }

    public static void main(String[] args) {
        long P = 23;  // Prime number
        long G = 5;   // Primitive root modulo P
        long a = 6;   // Alice's private key
        long b = 15;  // Bob's private key

        System.out.println("Prime number P: " + P);
        System.out.println("Primitive root G: " + G);
        System.out.println("Alice's private key a: " + a);
        System.out.println("Bob's private key b: " + b);

        // Compute public keys
        long x = power(G, a, P); // Alice's public key
        long y = power(G, b, P); // Bob's public key

        // Compute shared secret keys
        long ka = power(y, a, P); // Alice computes
        long kb = power(x, b, P); // Bob computes

        System.out.println("Alice's secret key: " + ka);
        System.out.println("Bob's secret key: " + kb);
    }
}
