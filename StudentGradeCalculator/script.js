document.getElementById("grade-form").addEventListener("submit", function(event) {
    event.preventDefault();

    const studentData = {
        name: document.getElementById("name").value,
        math: parseInt(document.getElementById("math").value),
        science: parseInt(document.getElementById("science").value),
        english: parseInt(document.getElementById("english").value),
    };

    fetch("http://localhost:8080/GradeCalculatorServlet", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(studentData),
    })
    .then(response => response.json())
    .then(data => {
        document.getElementById("result").innerText = 
            `Grade for ${studentData.name}: ${data.grade}`;
    })
    .catch(error => console.error("Error:", error));
});
