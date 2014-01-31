$(function () {

    $(":input:first").focus();

    var template = Handlebars.compile($('#product_template').text());
    function renderTable(data) {
        $('#listing').empty().append(template({gameConsoles:data}));
    };

    $(document).on("click", ".delete", function(e){
        e.preventDefault();
        deleteProducts($(this).data('id'));
    });

    var AeroProd = AeroGear.Pipeline([
        {
            name: "product",
            settings: {
                baseURL: "/aerogear/rest/"
            }
        }
    ]);

    Products = AeroProd.pipes[ "product" ];

    function retriveProducts() {
        Products.read({
            success: function (data) {
                renderTable(data);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(errorThrown);
                console.log("Error");
            }
        });
    }

    function deleteProducts(productId) {
        Products.remove( productId, {
            success: function (data) {
                retriveProducts();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(errorThrown);
                console.log("Error");
            }
        });
    }

    retriveProducts()

    $('#form_product').submit(function (e) {
        e.preventDefault();

        if( ($("#name").val() == '') || ($("#price").val() == '') ) {
            alert('Name and price are required');
        } else {
            Products.save(
                {
                    name:  $("#name").val(),
                    price: $("#price").val()
                },
                {
                    success: function (data, textStatus, jqXHR) {
                        retriveProducts();
                        $( "#form_product input").val('');
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        $(":input:first").focus();
                        alert(errorThrown);
                        console.log("Error");
                    }
                }
            );
        }
    });

});

