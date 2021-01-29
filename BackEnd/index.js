var express = require("express")
var app  = express()

var CoordinatesArray = [
                               ["Instagram" , "www.server.com"],
                               ["Facebook" ,"fb.com/server"]]
var UsersArray = [
                               ["test" , "4Bjx5WkqDQFVwdc1lUA4Yw==\n"],
                               ["sammu" ,"/puJxSckU46acuBrVHYWKw==\n"]]
app.use(express.json()); 

app.get("/", (req,res)=>(
    res.send("hello world")
))

app.post("/auth",(req,res)=>{
    let username  = req.body.username 
    let hashedPass = req.body.hashedPass
    console.log(username+"    "+hashedPass);
    
    let status = {
        'status': UsersArray[0][0] == username  }
        res.json(status)
    //TODO setup authentification here

})


app.post("/addCoordination",(req,res)=>{
    let username = req.body.username
    let type  = req.body.type 
    let value = req.body.value
    CoordinatesArray.push([type,value])
    console.log(CoordinatesArray);
    console.log(req.body)
    let status = {
        'status': true }
        res.json(status)
    //TODO setup authentification here

})


app.get("/userCoordinations/:username",(req,res)=>{
    let username = req.params.username
    console.log("sending coordinations")
    //TODO setup users infos here 
    let Coordinates = {
       "data" : CoordinatesArray
    }
    res.json(Coordinates)
})

app.get("/checkUser/:user",(req,res)=>{
    let username = req.params.user
    console.log(username)
    let status = {
    'status': true}
    res.json(status)
})

app.listen(2699);