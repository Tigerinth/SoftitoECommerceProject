$(document).ready(function () {
    const table = $("#accountTable").DataTable();
    let count = 0;
  
    $("#registerForm").validate({
      rules: {
        email: {
          required: true,
          email: true
        },
        name: {
          minlength: 5,
          required: true
        },
        username: {
          required: true
        },
        password: {
          minlength: 6,
          required: true
        },
        cpassword: {
          required: true,
          equalTo: "#passwordInput"
        }
      },
      errorElement: "div",
      errorPlacement: function (error, element) {
        error.addClass("invalid-feedback");
        error.insertAfter(element);
      },
      highlight: function (element) {
        $(element).removeClass("is-valid").addClass("is-invalid");
      },
      unhighlight: function (element) {
        $(element).removeClass("is-invalid");
      },
      submitHandler: function (form, e) {
        e.preventDefault();
        const username = $("#userInput").val();
        const email = $("#emailInput").val();
        const pass = $("#passwordInput").val();
        const name = $("#nameInput").val();
        const passInsert =
          "<span class='masked' id='passhide" +
          count +
          "'>" +
          pass +
          "</span><i class='fa fa-eye tablepass' id='iconPass" +
          count +
          "' onclick='tablePass(passhide" +
          count +
          ", iconPass" +
          count +
          ");'></i>";
        if (
          !$("#accountTable").colCheck(2, email, "email") &&
          !$("#accountTable").colCheck(3, username, "username")
        ) {
          count += 1;
          swal(
            "SUCCESS!",
            "We've successfully registered your account.",
            "success"
          );
          table.row.add([count, email, username, name, passInsert]).draw(false);
          const form = $("#registerForm");
          form.validate().resetForm();
          form[0].reset();
        }
        return false;
      }
    });
  });
  
  $.fn.colCheck = function (col, text, type) {
    var c = Array.isArray(col) ? col : [col],
      t = this,
      a = [];
  
    $.each(c, function (_, v) {
      a.push(
        t.find("tr td").filter(function () {
          return $(this).index() === v - 1 && $.trim($(this).text()) === text;
        }).length
      );
    });
  
    if (a[0] !== 0) {
      swal(
        "Error!",
        "The " + type + " you've entered already exists, please try again.",
        "error"
      );
      return true;
    }
    return false;
  };
  
  function tablePass(PassId, IconId) {
    $(IconId).toggleClass("fa-eye fa-eye-slash");
    if ($(PassId).hasClass("masked")) {
      $(PassId).removeClass("masked");
    } else {
      $(PassId).addClass("masked");
    }
  }
  