<html>
    <body>
        <form action="../RS_TodoManagement/rest/todos" method="POST">
            <label for="id">ID</label>
            <input name="id" />
            <br/>
            <label for="summary">Summary</label>
            <input name="summary" />
            <br/>
            Description:
            <TEXTAREA NAME="description" COLS=40 ROWS=6></TEXTAREA>
            <br/>
            <input type="submit" value="Submit" />
        </form>
        <input type="button" onclick="location.href='rest/todos/count';" value="Count" />
        
</body>
</html>
