let filesArray = [];
document.addEventListener("DOMContentLoaded", function() {
    // Select all drop areas
    const dropAreas = document.querySelectorAll(".drag-image");

    // Iterate over each drop area
    dropAreas.forEach(dropArea => {
        const index = dropArea.getAttribute('data-index');
        const button = dropArea.querySelector("button");
        const input = dropArea.querySelector("input");

        button.onclick = (event) => {
            event.preventDefault();
            input.click();
        };

        input.addEventListener("change", function() {
            const file = this.files[0];
            if (file) {
                filesArray[index] = file;
                
                dropArea.classList.add("active");
                viewFile(file, dropArea);
            }
        });

        dropArea.addEventListener("dragover", (event) => {
            event.preventDefault();
            dropArea.classList.add("active");
        });

        dropArea.addEventListener("dragleave", () => {
            dropArea.classList.remove("active");
        });

        dropArea.addEventListener("drop", (event) => {
            event.preventDefault();
            const file = event.dataTransfer.files[0];
            if (file) {
                filesArray[index] = file;
                
                dropArea.classList.add("active");
                viewFile(file, dropArea);
            }
        });

        // Function to preview the file
        function viewFile(file, dropArea) {
            let fileType = file.type;
            let validExtensions = ["image/jpeg", "image/jpg", "image/png"];
            if (validExtensions.includes(fileType)) {
                let fileReader = new FileReader();
                fileReader.onload = () => {
                    let fileURL = fileReader.result;
                    let imgTag = `<img src="${fileURL}" alt="image" style="max-width: 100%;">`;
                    dropArea.innerHTML = imgTag;
                };
                fileReader.readAsDataURL(file);
            } else {
                alert("This is not an Image File!");
                dropArea.classList.remove("active");
            }
        }

        // Initial preview from URL if available
        const imageUrl = dropArea.getAttribute("data-image-url");
        if (imageUrl) {
            previewImageFromUrl(imageUrl, dropArea);
        }
    });

    function previewImageFromUrl(url, dropArea) {
        if (url) {
            let imgTag = `<img src="${url}" alt="image" style="max-width: 100%;">`;
            dropArea.innerHTML = imgTag;
        }
    }
});


function appendFilesToFormData(formData) {
	 filesArray.forEach((file, index) => {
	        if (file) { 
	            formData.append(`files`, file);
	        }
	    });
}
