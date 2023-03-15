// Adgang til header elementet, ID = # først, CLASS = . først
const getall = document.querySelector("#getall")
const hobbysearch = document.querySelector("#hobbysearch")
const more = document.querySelector("#more")
const evenmore = document.querySelector("#evenmore")


const msg = document.querySelector("#msg")

function displayMessage(msg){
    document.querySelector("#msg").textContent = msg;
}

getall.addEventListener('click',event)=>{
    fetch(`http://localhost:8080/ca1/api/person`)
        .then(response=>response.json())
        .then(data =>)
}