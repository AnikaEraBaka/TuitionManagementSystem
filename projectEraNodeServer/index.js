const Express  = require('express')
const Mongodb  = require('mongodb')
const Mongoose = require('mongoose')

var app = Express()
var teacherProfileSchema = new Mongoose.Schema({
    name: String,
    pass: String,
    email: String,
    phn: String,
    address: String,
    sub1 : String,
    sub2 : String,
    sub3 : String,
})
var teacherProfileCollection = Mongoose.model('teacherProfileCollection', teacherProfileSchema)
var studentProfileCollection = Mongoose.model('studentProfileCollection', teacherProfileSchema)

Mongoose.connect('mongodb+srv://Taufiq:anikapochameye@cluster0-aeqya.mongodb.net/chatIt?retryWrites=true&w=majority',{ useNewUrlParser: true, useUnifiedTopology: true })

app.get('/signUpTeacher',(req, res)=>
{
    console.log(req.query)
    teacherProfileCollection({
        name: req.query.name,
        pass: req.query.pass,
        email: req.query.email,
        phn: req.query.phn,
        address: req.query.address,
        sub1 : req.query.sub1,
        sub2 : req.query.sub2,
        sub3 : req.query.sub3,
    }).save((error)=>
    {
        if(error)
            throw error;
        else
            console.log("New teacher's profile added.");
    })
})

app.get('/signUpStudent',(req, res)=>
{
    console.log(req.query)
    studentProfileCollection({
        name: req.query.name,
        pass: req.query.pass,
        email: req.query.email,
        phn: req.query.phn,
        address: req.query.address,
        sub1 : req.query.sub1,
        sub2 : req.query.sub2,
        sub3 : req.query.sub3,
    }).save((error)=>
    {
        if(error)
            throw error;
        else
            console.log("New student's profile added.");
    })
})

app.get('/logInTeacher',(req, res)=>
{
    console.log(req.query)
    teacherProfileCollection.find(
        {
            pass : req.query.pass,
            email : req.query.email
        },
        (error, data)=>
        {
            console.log(data)
            if(error)
                throw error;
            else
            {
                if(data.length > 0)
                    res.send("true")
                else
                    res.send("false")
            }
        }
    )
})

app.get('/logInStudent',(req, res)=>
{
    console.log(req.query)
    studentProfileCollection.find(
        {
            pass : req.query.pass,
            email : req.query.email
        },
        (error, data)=>
        {
            console.log(data)
            if(error)
                throw error;
            else
            {
                if(data.length > 0)
                {
                    res.send("true")
                   //console.log("Till here")
                }
                else
                    res.send("false")
            }
        }
    )
})

app.get('/studentSection',(req, res)=>
{
    console.log(req.query)
    teacherProfileCollection.find(
        {
            address : req.query.address
        },
        (error, data)=>
        {
            console.log(data)
            if(error)
                throw error;
            else
            {
                if(data.length > 0)
                {
                    var response = ""
                    for(var i = 0; i < data.length;i++)
                    {
                        response += data[i].name + '\n' + data[i].phn + '\n' + data[i].sub1 + ' ' + data[i].sub2 + ' ' + data[i].sub3 + '\n\n'
                    }
                    console.log(response)
                    res.send(response)
                }
                else
                    res.send("false")
            }
        }
    )
})

app.get('/teacherSection',(req, res)=>
{
    console.log(req.query)
    studentProfileCollection.find(
        {
            address : req.query.address
        },
        (error, data)=>
        {
            console.log(data)
            if(error)
                throw error;
            else
            {
                if(data.length > 0)
                {
                    var response = ""
                    for(var i = 0; i < data.length;i++)
                    {
                        response += data[i].name + '\n' + data[i].phn + '\n' + data[i].sub1 + ' ' + data[i].sub2 + ' ' + data[i].sub3 + '\n\n'
                    }
                    console.log(response)
                    res.send(response)
                }
                else
                    res.send("false")
            }
        }
    )
})
app.listen(3000, ()=>
{
    console.log("Listening to port 3000")
})