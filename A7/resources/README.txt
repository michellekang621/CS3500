Assignment 7:

Changes to model:
1. Added updateCell() method to update the cell whenever the user wanted to update
the contents of the cell.
2. We created a new model implementing the use of the Cell class.
3. We added getWorksheet() to both models to avoid making the field public.

Changes to view:
1. We made SpreadsheetView implement WorksheetView and added the methods that were used for the new
    view (ControllableView) to the SpreadsheetView and made them throw
    UnsupportedOperationArguments.