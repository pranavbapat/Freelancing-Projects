#include<iostream>
#include<string.h>

using namespace std;

//PhoneEntry Class represents each entry in the PhoneBook
class PhoneEntry
{
    //Private access variables
    private:
        //Name and number of contact
        string myNumber;
        string myName;

    //Public access specifier
    public:
        //Setter method to set the number which is a private variable
        void setNumber(string num)
        {
           myNumber=num;
        }

        //Setter method to set the name which is a private variable
        void setName(string name)
        {
            myName=name;
        }

        //Getter method to get Number
        string getNumber()
        {
            return myNumber;
        }

        //Getter method to get Name
        string getName()
        {
            return myName;
        }
};

//PhoneBook class represents multiple PhoneEntries
class phoneBook
{
    //Private Access Specifier
    private:
        //Static and constant integer to represent maximum number of entries in the phonebook
        static const int MAX_ENTRIES=10;

        //Array of PhoneEntry objects to represent each entry in the phonebook
        PhoneEntry ph[MAX_ENTRIES];

        //Static variable to keep count of number of entries currently in the phonebook
        static int currentCount;

    //Public Access Specifier
    public:
        //Method to add new entry in the phoneBook
        void add(string name,string number)
        {
            ph[currentCount].setName(name);
            ph[currentCount].setNumber(number);

            //Incrementing current counter to represent added entry
            currentCount++;
        }

        //Function that returns reference to the entry at the specified position
        const PhoneEntry& phoneEntryAt(int position) const
        {
            //If position is less than 0 return the first entry in the phonebook
            if(position < 0)
                return ph[0];
            //If position is greter than maximum entries, return the last entry in the phonebook
            else if(position > currentCount)
                return ph[currentCount];
            //If position is valid, then return entry at that position
            else
                return ph[position];
        }

        //Method to remove Entry by Name
        void removeByName(string name)
        {
            int i=0,j=0;
            for(i=0;i<currentCount;i++)
            {
                //When entry with a matching name is found
                if(ph[i].getName() == name)
                {
                    //Shift all entries one index up
                    for(j=0;j<currentCount;j++)
                    {
                        ph[j].setName(ph[j+1].getName());
                        ph[j].setNumber(ph[j+1].getNumber());
                    }
                    //Decrement current count variable to represent the removed entry
                    currentCount--;
                }
            }
        }

        //Method to remove entry by Number
        void removeByNumber(string number)
        {
            int i=0,j=0;
            for(i=0;i<currentCount;i++)
            {
                //When a matching number is found
                if(ph[i].getNumber() == number)
                {
                    //Shift all subsequent entries one index up in the array
                    for(j=0;j<currentCount;j++)
                    {
                        ph[j].setName(ph[j+1].getName());
                        ph[j].setNumber(ph[j+1].getNumber());
                    }
                    //Decrement current count variable to represent the removed entry
                    currentCount--;
                }
            }
        }

        //Method to return the size of the phoneBook
        int getSize()
        {
            return currentCount;
        }
};

//Declaration of static variable currentCount
int phoneBook::currentCount;

int main()
{
    //Object of phoneBook class
    phoneBook book;

    //Object of PhoneEntry class
    PhoneEntry ph;

    int choice=0;

    //Character to know if user wants to continue
    char menuChoice='y';

    //Temporary variables for storing name and number for operations
    string name, number;

    //Position variable for getEntryAt method
    int position;

    while(menuChoice == 'y')
    {
        cout << "1. Add an entry \n";
        cout << "2. Remove entry by name \n";
        cout << "3. Remove entry by number \n";
        cout << "4. Get total count \n";
        cout << "5. Get entry at position \n";
        cout << ":: ";
        cin >> choice;

        switch(choice)
        {
            case 1:
                    cout <<"Enter Name and Number: ";
                    cin >> name >> number;
                    book.add(name, number);
                    cout << "\nEntry Added!";
                    break;
            case 2:
                    cout << "Enter Name to remove: ";
                    cin >> name;
                    book.removeByName(name);
                    break;
            case 3:
                    cout << "Enter number to remove: ";
                    cin >> number;
                    book.removeByNumber(number);
                    break;
            case 4:
                    cout << "Total Count: " << book.getSize();
                    break;

            case 5:
                    const PhoneEntry myReturnObj;
                    cout << "Enter Position: ";
                    cin >> position;
                    PhoneEntry temp = book.phoneEntryAt(position);
                    cout << "Name: " << temp.getName();
                    cout << "Number: " << temp.getNumber();
                    break;
        }

        cout << "\nDo you want to Continue? ";
        cin >> menuChoice;
    }

    return 0;
}
