const API_KEY = "d0be149fb9d44dfeb36f46dcbd5a66f6"

function setRecipes() {
    showRecipes('healthy');
}

function buildRecipe(recipe) {
    return '<div class="col-xl-4 col-lg-4 col-md-6">' +
        '   <div class="single_recepie text-center">' +
        '       <div class="recepie_thumb">' +
        '           <img src="'+recipe.image+'" alt="">' +
        '       </div>\n' +
        '       <h3>'+recipe.title+'</h3>' +
        '       <a href="/info/recipes/'+recipe.id+'" class="line_btn">View Full Recipe</a>' +
        '   </div>' +
        '</div>';
}

function showRecipes(search, intolerances) {
    $.ajax({
        url:`https://api.spoonacular.com/recipes/complexSearch?query=${search}&intolerances=${intolerances}&number=30&apiKey=${API_KEY}`,
        success: function(res) {
            console.log(res);
            var recipes = res.results;
            var view = "";
            for (i=0; i<recipes.length; i++) {
                view += buildRecipe(recipes[i]);
            }
            document.getElementById("recipes").innerHTML = view;
        },
        error: function (request, status, error) {
            alert(request.responseText);
        }
    });
}

window.onload = function() {
    setRecipes();
}

$( "#searchQuerySubmit" ).click(
    function() {
        var search = $( "#searchQueryInput" ).val();
        if(search.empty)
            search = 'healthy';
        showRecipes(search.trim(), getIntolerances());
    }
);

var intolerances = [];

$( ".i_line_btn" ).click(
    function() {
        if($(this).css("background").includes("rgb(224, 156, 35)") ||
            $(this).css("background").includes("rgb(255, 255, 255)")){
            $(this).css('background','#e09c22');
            $(this).css('color','#fff');
            intolerances.push($(this).attr("value"));
        } else {
            $(this).css('background','#fff');
            $(this).css('color','#000000');
            var val = $(this).attr("value");
            intolerances = intolerances.filter(function(item) {
                return item !== val;
            })
        }
        var search = $( "#searchQueryInput" ).val();
        if(!search)
            search = 'healthy';
        showRecipes(search.trim(), getIntolerances());
    }
);

function getIntolerances() {
    var intolerancesList = "";
    intolerances.forEach(function(item) {
        intolerancesList += item + ',';
    });
    return intolerancesList.slice(0, -1);
}

function buildRecipeInfo() {
    return '<div class="popup" id="popup">' +
        '    <div class="popup-inner">' +
        '      <div class="popup__photo">' +
        '        <img src="https://images.unsplash.com/photo-1515224526905-51c7d77c7bb8?ixlib=rb-0.3.5&s=9980646201037d28700d826b1bd096c4&auto=format&fit=crop&w=700&q=80" alt="">' +
        '      </div>' +
        '      <div class="popup__text">' +
        '        <h1>Lorem ipsum dolor sit amet</h1>' +
        '        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer ex velit, viverra non vulputate vitae, blandit vitae nisl. Nullam fermentum orci et erat viverra bibendum. Aliquam sed varius nibh, vitae mattis purus. Mauris elementum sapien non ullamcorper vulputate. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Sed eget felis sit amet eros viverra pulvinar.</p>' +
        '      </div>' +
        '      <a class="popup__close" href="#">X</a>' +
        '    </div>' +
        '  </div>';
}