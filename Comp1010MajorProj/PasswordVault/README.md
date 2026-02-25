# 𝄃𝄃𝄂𝄂𝄀𝄁𝄃𝄂𝄂𝄃 THE PASSWORD VAULT 𝄃𝄃𝄂𝄂𝄀𝄁𝄃𝄂𝄂𝄃 #

*By Lilly Nicholls 48862428 COMP1010 Major Project*

## What Problem Does My Application Solve? ##

Passwords are what protect our personal information from being exposed, stolen and turned against us. In the modern era, with techniques like social engineering, insider threat and password spraying on the rise, we should strive to have better cyber awareness. My application provides users with a local offline source to store their passwords in a soft-encryption to encourage more complex and useful passwords. 

## Description ##

Welcome to the PASSWORD VAULT ◝(ᵔᵕᵔ)◜ a local password storage for users to create and store their own sites, usernames, and passwords! Your vault is password protected to view and add to the contents and is soft-encrypted to deter harmful actors from immeaditely knowning your personal data! The PASSWORD VAULT also offers examples of different levels of passwords, and general tips and tricks for when making your own passwords!

## Structure ##

★ Cred.java -> **OBJECT** ★

- Credential Object: site, username, password
- Stores the credentials, with getters and setters 
- Has a toString() method to aid in displaying the information

★ Example.java -> **RECURSIVE OBJECT** ★

- Example Password Object: password, length, next example
- Stores the example password, the length of the password and the next next Example in the linked list.

★ User.java -> **OBJECT** ★

- User Object: username, password
- Stores the user objects (for the vaults), with getters and setters
- Has a toString() method to aid in displaying the information

★ Vault.java -> **OBJECT** ★

- Vault Object: credentials ArrayList<Cred>, User Object -> user
- A method to add a credential to the array list
- A saveToFile method which adds the credential to the individuals vault file, encrypting it BEFORE it is saved to the file. 
- a loadFromFile method to decrypt the credentials before preparing to display them with the displayCredentials method which uses the toString() from the credential object. 
- Also the getters and setters for the vault. 

★ Encryption.java -> **FUNCTIONS** ★

- Encryption function which includes the range (ASCII range), shift and an ArrayList of the encrytedChars. 
- It then encrypts the information using a Ceasers Cypher and then adding the chars to the arraylist. 
- There is also a decrypt function which returns the decrypted password USING the encrypt function but backwards. 

★ EncryptionTest.java -> **TESTING** ★

- 10 tests
- Includes simple test, empty test, negative and large shifts, all printable characters, only special characters, only numbers, single character and a very long string. 

★ ExampleTest.java -> **TESTING** ★
 
- 12 tests
- Includes creating single node, linking multiple nodes, empty passwords, null passwords, traversing the list, total length test, count test, null head, negative length, long list and helper method.

★ VaultTest.java -> **TESTING** ★

- 10 tests
- Includes normal crednetional, adding credential that is empty, adding credential with null, loading and saving credentials with special characters, saving and loading long strings, non-existent file loading, saving and loading with numbers and border stuff, adding multiple credentials, displaying an empty vault, and adding max capacity.

★ PasswordExamples.java -> **EXAMPLES LINKED LIST** ★

- Easy, Medium and Hard examples.
- Turns them all into linked lists connected to one another.

★ TipsAndTricksScreen.java -> **LINKED LIST/RECURSION** ★

- Displays all the easy, medium and hard examples as well as the average length of each group.
- Average length is calculated using two recursive methods to count and then sum the totals!
- Displays the tips at the end.

★ VaultDriver.java -> **DRIVER** ★

- The executable file
- Has the log in loop as well as the menu which allows the user to add credentials, view credentials, log out, exit, or view the tips and tricks. 
- There are many seperate functions to promote delegation and readability

★ VaultClient.java -> **CLIENT** ★

- Takes all of the user input
- Also has the waitForEnter() function which makes the program more user friendly.

★ AllVaults -> **FOLDER** ★

↳ *user.txt*
- Holds all of the user information encrypted by the encryption function

↳ *username_vault.txt*
- Holds all the users credentials encrypted by the encryption function


- List of each class/file and what they do etc -> 

## Instructions ##

To execute the program, locate the VaultDriver.java file. 

Once you are in the VaultDriver.java file press RUN -> Start without Debugging OR the PLAY ▶ button in the top right corner. And you are good to go!

1. Enter your username (if you don't have a vault, thats okay, it will make you one!)
2. Enter your password 
3. Congrats you're in! (or not if you got your password wrong...)
4. Pick what you want to do...

*✚ ADD A CREDENTIAL ✚*
1. When prompted by the menu, enter "1" to add a credential to your vault
2. Enter the site (make this recognisable so you know it from a list!)
3. Enter your username
4. MOST IMPORTANT! Enter your password (make sure you get it right!)
5. Now it has been encrypted and added to your vault!
6. Don't forget to press enter to return back to the menu!

*⌞ ⌝ VIEW CREDENTIALS ⌞ ⌝*
1. When prompted by the menu enter "2" to view the credintials in your vault.
2. It will then display your credentials in the order you have added them. 
3. Don't forget to press enter to return back to the menu!

*↪ LOG OUT ↪*
1. When prompted by the menu enter "3" to log out
2. If you are just looking to close the system, make sure you use the exit function instead. 
3. You will then be taken back to the log in process!

*➜] EXIT THE PASSWORD VAULT ➜]*
1. When prompted by the menu enter "4" to exit the password vault
2. It will then close the program automatically!

*◝(ᵔᵕᵔ)◜ LOOKING FOR TIPS, TRICKS, AND EXAMPLES? ◝(ᵔᵕᵔ)◜*
1. When prompted by the menu enter "5" to access the tips and tricks. 
2. You will then be greeted by a list of different levels of passwords; easy, medium and hard, which you will be able to easily tell the difference between. It also displays the average length of all these passwords. 
3. It will then show you a list of simple tips and tricks to ensure you are choosing good passwords!
4. Don't forget to press enter to return back to the menu!