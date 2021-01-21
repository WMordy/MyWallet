var express = require("express")
var app  = express()


app.use(express.json()); 

app.get("/", (req,res)=>(
    res.send("hello world")
))

app.post("/auth",(req,res)=>{
    let username  = req.body.username 
    let hashedPass = req.body.hashedPass
    console.log(username+"    "+hashedPass);
    console.log(req.body)
    res.sendStatus(200)
    //TODO setup authentification here

})

app.get("/userCoordinations/:username",(req,res)=>{
    let username = req.params.username
    console.log(username)
    //TODO setup users infos here 
    let Coordinates = {
        "ig" : "www.server.com",
        "facebook" : "fb.com/server"
    }
    res.json(Coordinates)
})

app.get("/checkUser/:user",(req,res)=>{
    let username = req.params.user
    console.log(username)
    res.sendStatus(200)
})

app.listen(2699);