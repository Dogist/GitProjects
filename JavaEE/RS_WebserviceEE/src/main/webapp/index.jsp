<html>
    <body>
        <h2>Jersey RESTful Web Application!</h2>
        <p><a href="webapi/myresource">Jersey resource</a></p>
        <p><a href="webapi/users/noplan">User ressources</a></p>


        <form method="post" action="webapi/myresource/create">
            Order Number: <input type="text" name="id"/><br/>
            Customer Name: <input type="text" name="name"/><br/>
            <input type="submit" value="Create Order"/>
        </form>

    </body>
</html>
