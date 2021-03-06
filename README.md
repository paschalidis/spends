# spends

Application for recording and monitoring the user's daily expenses.
Project for [MSc in Software Engineering for Internet and Mobile Application](http://msc.cs.teilar.gr/info/)

## User Experience

### 1. Creating Categories of Expenses
In this use cases the user will:
  * Creates different categories of expenses
  * The categories will have a concise sentence and a full description that will be
Optional
  * Categories will be stored in a SQLite DB

### 2. Recording of expenses
In this case the user will do the full
Recording an output:
  * The system will retrieve the date / time Of the mobile that will be the date / time on which Exit took place
  * The user will give a description of the output optionally and will necessarily specify the exit class by choosing as a category one of the ones he created in (1)
  * If no category has yet been created, it should be enabled to create it the screen anyway
  * The user will necessarily give his monetary value exit
  * It will also record the location through Google's Location Services APIs
  * The user will record the location that was exited and he will optionally name it. 
  * If he goes back to the same location in the future he will the saved name automatically appears as a recommendation
  * All the items will stored in SQLite DB

### 3. Inspection of expenses
In this case, the user will inspect all expenses in a list:
  * He will be able to click on an exit when he will see his details
  * If he wants, he can delete and modify the output.
  * Modification of the output may include:
    * A. Change output description
    * B. Change output value
    * C. Change output category
    
### 4. Expenditure Analysis
In this case, the user will give an analysis session with a start date and a end date:
  * The system will present all expense categories made in this period in descending order of the total value

