/**
 * Project: Movie Database Management System
 * Author: Fin Martinez with help from ChatGPT and CoPilot
 * Date: 11 October 2024
 * Version 2.0
 * 
 * Description:
 * This Java project implements a Movie Database Management System that allows users
 * to store, sort, and search movie data. The project includes various data structures 
 * like Binary Search Trees (BST) and Singly Linked Lists (SLL) to store movie records.
 * 
 * Features:
 * - Sorting: Supports Bubble Sort, Merge Sort, Quick Sort, and Selection Sort.
 * - Searching: Binary Search, Linear Search, and title-based search using a SinglyLinkedList.
 * - Data Structures: Offers options for Binary Search Tree (BST) and Singly Linked List (SLL)
 *   for storing movie data.
 * 
 * Usage:
 * The system allows the user to input movie information, select a storage option (BST or SLL),
 * and perform sorting or searching operations.
 * 
 * Last Updated: 11 October 2024
 * 
 * Contact:
 * fmarti74@msudenver.edu, finmtz@gmail.com
 * https://github.com/FinMartinez
 */

package movieDatabase;

public class MovieDBmain {
    public static void main(String[] args){
        MovieDb movieDb = new MovieDb();
        movieDb.loadFromCSV("movies_list.csv");
        MainMenu displayMenu = new MainMenu(movieDb);
        displayMenu.start();
        }
}

