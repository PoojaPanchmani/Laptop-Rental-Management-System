<!DOCTYPE html>
<html>
<head>
<link href="Accountend.css" rel="stylesheet">
</head>
<body>
        <div class="account_head">
            <p>Your Profile</p>
        </div>
        <div class="account_detail">
            <div class="account_title">
                <p>Your Name</p>
            </div>
            <div class="account_title_body">
                <p>${userdata.name}</p>
            </div>
        </div>
        <div class="account_detail">
            <div class="account_title">
                <p>Email Detail</p>
            </div>
            <div class="account_title_body">
                <p>${userdata.email}</p>
            </div>
        </div>
        <div class="account_detail">
            <div class="account_title">
                <p>Contact Detail</p>
            </div>
            <div class="account_title_body">
                <p>${userdata.number}</p>
            </div>
        </div>
</body>
</html>