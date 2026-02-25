public class PasswordExamples {

    // Easy, Medium, Hard example linked list heads
    public Example easyHead, medHead, hardHead;

    /*
     * This is to show EXAMPLES of what to do/what not to do when creating passwords
     * It includes the password and the length of the password
    */
    public PasswordExamples() {
        // Easy Examples
        easyHead = new Example("password", 8, null);
        Example easy2 = new Example("Password123", 11, null);
        Example easy3 = new Example("08062006", 8, null);
        Example easy4 = new Example("Cake1!", 6, null);
        Example easy5 = new Example("Myname@123", 10, null);

        // Setting the Linked List
        easyHead.next = easy2;
        easy2.next = easy3;
        easy3.next = easy4;
        easy4.next = easy5;

        // Medium Examples
        medHead = new Example("mYN@m374s7n@m3", 14, null);
        Example med2 = new Example("14560n141025", 12, null);
        Example med3 = new Example("C0mP1010i5f^n", 13, null);
        Example med4 = new Example("7e7s605h4rk52025", 16, null);
        Example med5 = new Example("7r34llyL0^ey0u", 14, null);

        // Setting the Linked List
        medHead.next = med2;
        med2.next = med3;
        med3.next = med4;
        med4.next = med5;

        // Hard Examples
        hardHead = new Example("6oH9F$%1ZLuG3£4{F/kV", 20, null);
        Example hard2 = new Example("ustortoRYPHaRaThErAwRiTI", 24, null);
        Example hard3 = new Example("e1F)%2^8N\"-#wBG\\q^v7cs", 22, null);
        Example hard4 = new Example("rc2W521;MT9#[}Rhqq@b&qf/", 24, null);
        Example hard5 = new Example("vUAv7v8SHO5l/jm4R4q(5;~-", 24, null);

        // Setting the Linked List
        hardHead.next = hard2;
        hard2.next = hard3;
        hard3.next = hard4;
        hard4.next = hard5;
    }
}
