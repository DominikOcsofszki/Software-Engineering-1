<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
    <meta name="author" content="Team89 (C) 2021">
    <meta name="description" content="Software Engineering 1 (SE1)">
    <meta name="license" content="The MIT License (MIT)">
    <meta name="theme-color" content="#239BD1"/>
    <meta property="og:title" content="Software Engineering 1 Project(SE1)">
    <meta property="og:description" content="Bachelor Course Software Engineering 1 (SE1), Hochschule Bonn-Rhein-Sieg.">
    <link rel="shortcut icon" href="https://kaul.inf.h-brs.de/favicon.ico" />
    <title>Tomcat Parkhaus</title>
    <script src="https://kaul.inf.h-brs.de/ccmjs/mkaul-components/parkhaus/versions/ccm.parkhaus-11.0.0.js"></script>
    <style>
        * {
            font-family: sans-serif, Helvetica, Arial;
        }
        .grey-background {
            background-color: lightgrey;
        }
        .center {
            text-align: center;
        }
        .box {
            border: thin solid black;
            margin: 0.5rem 0;
            padding: 1rem;
        }
        .lightblue {
            background-color: #d0ebf6;
        }
        .lightyellow {
            background-color: lightgoldenrodyellow;
        }
        .lightgreen {
            background-color: #ccfdcc;
        }
    </style>
</head>
<body>
<div class="box center grey-background">
    <h1>Park house Team 6</h1>
    <p>Tomcat Version : <%= application.getServerInfo() %></p>
</div>
<div class="box lightblue">
    <h2><a href="checkout.jsp">Checkout</a></h2>
    <h2><a href="">Management</a></h2>
    <h2><a href="">Accounting</a></h2>
    <h2><a href="">Subscriptions</a></h2>
</div>
<div class="box grey">
    <h1>Park house</h1>
    <ccm-parkhaus-11-0-0 server_url="./main-servlet"
                         debug="true"
                         name="Park house"
                         license_min="1"
                         license_max="100"
                         open_from="4"
                         open_to="23"
                         delay="100"
                         simulation_speed="1000"
                         extra_buttons='["Sum","Avg","Min","Max","Table","Daily-Earnings","Weekly-Earnings","Current-Cost"]'
                         extra_charts='["Types"]'
                         client_categories='["Default","Women","Business","SUV","Family","Disability"]'
                         space_color='{"1":"black"}'
                         vehicle_types='["PKW","SUV"]'
                         price_factor='{"SUV":2,"Family":0.5}'
                         max="32"
                         SALT="456">
    </ccm-parkhaus-11-0-0>
</div>
</body>
</html>