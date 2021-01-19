var express = require("express")
var app  = express()

app.get("/", (req,res)=>(
    res.send("hello world")
))

app.post("/auth",(req,res)=>{
    //TODO setup authentification here
})

app.get("/userCoordinations",(req,res)=>{
    //TODO setup users infos here 
    res.send("coordinates")
})

app.listen(5000);