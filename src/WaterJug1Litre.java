/**
 * Measure one litre using two vessels and infinite water supply
 * There are two vessels of capacities 'a' and 'b' respectively. We have infinite water supply. Give an efficient algorithm to make exactly 1 litre of water in one of the vessels. You can throw all the water from any vessel any point of time. Assume that 'a' and 'b' are Coprimes.
 * Following are the steps:
 * Let V1 be the vessel of capacity 'a' and V2 be the vessel of capacity 'b' and 'a' is smaller than 'b'.
 * 1) Do following while the amount of water in V1 is not 1.
 * ....a) If V1 is empty, then completely fill V1
 * ....b) Transfer water from V1 to V2. If V2 becomes full, then keep the remaining water in V1 and empty V2
 * 2) V1 will have 1 litre after termination of loop in step 1. Return.
 */
public class WaterJug1Litre {
    public static void main(String[] args) {
        int a = 3, b = 7; // a must be smaller than b

        // Create two vessels of capacities a and b
        Vessel V1 = new Vessel(a);
        Vessel V2 = new Vessel(b);

        // Get 1 litre in first vessel
        V1.makeOneLitre(V2);
    }
}

// Class to represent a Vessel
class Vessel
{

    // A vessel has capacity, and
    // current amount of water in it
    int capacity, current;

    // Constructor: initializes capacity
    // as given, and current as 0
    public Vessel(int capacity)
    {
        this.capacity = capacity;
        current = 0;
    }

    private int gcd(int a, int b) {
        return b == 0? a : gcd(b, a%b);
    }

    // The main function to fill one litre
    // in this vessel. Capacity of V2 must be
    // greater than this vessel and two capacities
    // must be coprime
    void makeOneLitre(Vessel V2)
    {
        // solution exists if a and b are co-prime
        if (gcd(capacity, V2.capacity) != 1)
            return;

        while (current != 1)
        {
            // fill A (smaller vessel)
            if (current == 0)
                current = capacity;

            System.out.print("Vessel 1: " + current +
                    " Vessel 2: " + V2.current + "\n");

            // Transfer water from V1 to V2 and
            // reduce current of V1 by
            // the amount equal to transferred water
            current = current - V2.transfer(current);
        }

        // Finally, there will be 1 litre in vessel 1
        System.out.print("Vessel 1: " + current +
                " Vessel 2: " + V2.current + "\n");
    }

    // Fills vessel with given amount and
    // returns the amount of water
    // transferred to it. If the vessel
    // becomes full, then the vessel
    // is made empty
    int transfer(int amount)
    {
        // If the vessel can accommodate the given amount
        if (current + amount < capacity)
        {
            current += amount;
            return amount;
        }

        // If the vessel cannot accommodate
        // the given amount, then store
        // the amount of water transferred
        int transferred = capacity - current;

        // Since the vessel becomes full, make the vessel
        // empty so that it can be filled again
        current = 0;

        return transferred;
    }
}
