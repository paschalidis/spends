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
