// Adgang til header elementet, ID = # først, CLASS = . først
const getall = document.querySelector("#getall")
const hobbysearch = document.querySelector("#hobbysearch")
const more = document.querySelector("#more")
const evenmore = document.querySelector("#evenmore")




// PEOPLE
let people = [];

function displayPeople(){
    // let string = "";
    // for(let i=0;i<people.length;i++) {
    //     string += people.get(i) + "/n";
    // }
    // console.log(string)
    document.querySelector("#msg").innerHTML = "";
    document.getElementById("msg").innerHTML = people.join("<br>");

}

function addPerson(data){
    people = [];
    for(let i=0;i<data.length;i++) {
        const person = {
            email: data[i].email,
            firstname: data[i].firstname,
            gender: data[i].gender,
            lastname: data[i].lastname,
            relationshipStatus: data[i].relationshipStatus,

            toString() {
                return `${this.firstname} ${this.lastname} ${this.email} ${this.gender} ${this.relationshipStatus}`
            }
        }
        people[i] = person
    }
    console.log("efter loop" + people)
}


getall.addEventListener('click', (event)=>{
    // const input = document.querySelector("#text").value;
    fetch(`https://callan.dk/tomcat/ca1/api/person/`)
        .then(response=>response.json())
        .then(data=>addPerson(data))
        .then(displayPeople)
        .catch(error=>console.log("An error has occured."))
})


// HOBBY
hobbysearch.addEventListener('click', (event)=>{
    const input = document.querySelector("#text").value;
    fetch(`https://callan.dk/tomcat/ca1/api/hobby/${input}`)
        .then(response=>response.json())
        .then(data=>addPerson(data))
        .then(displayPeople)
        .catch(error=>console.log("An error has occured."))
})

// MORE
more.addEventListener('click', (event)=>{
    console.log("CLICKED on more")
})

// EVEN MORE
evenmore.addEventListener('click', (event)=>{
    console.log("CLICKED on EVEN MORE!")
})