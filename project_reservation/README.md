# Restaurant Reservation System

## Phase 1 

**Project description**

My application will help restaurant to handle reservation of a day. People who work in a restaurant will find it useful.
The reason I developed this project, because I have noticed
some restaurants are still recording their reservations by hands. I design this application hope to help them work 
in a more efficient way and provide convenience to the guests as well.

A *user story* list:
- As a user, I want to be able to add a reservation to a list of reservations of a day.
- As a user, I want to be able to view the list of reservations of a day.
- As a user, I want to verify if a customer has reservation on a day.
- As a user, I want to change the time of a customer's reservation on a day.
- As a user, I want to be able to save the reservations of a day to file
- As a user, I want to be able to be able to load the reservations of a day from file 

## Phase 4: Task 2
**Robustness Implementation**

I implemented AddCustomerInvalidException to addCustomertoDay(), and CustomerNotFoundException to the 
changeBookTime(), and verifyBooking() methods in the Dayreservation class.

I add the two exception classes (AddCustomerInvalidException and CustomerNotFoundException) to increase the
robustness of my project. I add AddCustomerInvalidException as a RuntimeException Exception 
because it plays a key role in making sure the input string from the user end is valid and will not cause the 
whole system to just break, when user input invalid strings for Customer name and Customer phone number. Specifically,
AddCustomerInvalidException will check if the name has only alphabets, and the input phone # has only numbers.

The reason I added CustomerNotFoundException is also to eliminate the risk of the user who are verifying and changing
the info in the reservation system but the corresponding customer isn't contained in the list. By throwing this
exception when the customer is not in the list, and caught it at the gui, the user will get an pop-up window with
notification that the customer is not found.

By constructing and testing these two exceptions, all the user stories are
not only fulfilled but also achieved in a robust and secure way.

## Phase 4: Task 3
**Refactoring for improvement**

1. Delegate three classes from the gui to reduce code duplication: 

I would further refactor the ui package, specifically the three listener classes (AddListener,VerifyListener, 
 ChangeListener). Since all of them have some fields in common and have similar implementation in the constructors, 
 All of them implements ActionListener, DocumentListener as well. Therefore, I would further refactor those 
 three classes to an abstract class called ListenerManager that will implements ActionListener, DocumentListener. 
 The three classes will therefore extend ListenerManager and further implement the abstract methods which 
 perform their unique functionality when they are called.
 
 
