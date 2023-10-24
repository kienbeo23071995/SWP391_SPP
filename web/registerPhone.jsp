<!-- /*
* Template Name: Learner
* Template Author: Untree.co
* Tempalte URI: https://untree.co/
* License: https://creativecommons.org/licenses/by/3.0/
*/ -->
<!doctype html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="author" content="Untree.co">
        <link rel="shortcut icon" href="favicon.png">

        <meta name="description" content="" />
        <meta name="keywords" content="bootstrap, bootstrap4" />

        <link href="https://fonts.googleapis.com/css2?family=Display+Playfair:wght@400;700&family=Inter:wght@400;700&display=swap" rel="stylesheet">


        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">

        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/animate.min.css">
        <link rel="stylesheet" href="css/owl.carousel.min.css">
        <link rel="stylesheet" href="css/owl.theme.default.min.css">
        <link rel="stylesheet" href="css/jquery.fancybox.min.css">
        <link rel="stylesheet" href="fonts/icomoon/style.css">
        <link rel="stylesheet" href="fonts/flaticon/font/flaticon.css">
        <link rel="stylesheet" href="css/aos.css">
        <link rel="stylesheet" href="css/style.css">

        <title>Learner Free Bootstrap Template by Untree.co</title>

    </head>

    <body>

        <div class="untree_co-hero inner-page overlay" style="background-image: url('images/img-school-5-min.jpg');">
            <div class="container">
                <div class="row align-items-center justify-content-center">
                    <div class="col-12">
                        <div class="row justify-content-center ">
                            <div class="col-lg-6 text-center ">


                            </div>
                        </div>
                    </div>
                </div> <!-- /.row -->
            </div> <!-- /.container -->

        </div> <!-- /.untree_co-hero -->




        <div class="untree_co-section">
            <div class="container">

                <div class="row mb-5 justify-content-center">
                    <div class="col-lg-5 mx-auto order-1" data-aos="fade-up" data-aos-delay="200">
                        <form action="registerPhone" method="post" class="form-box">
                            <div class="row">
                                <div class="col-12 mb-3">
                                    <h1 class="mb-4 heading text-center" data-aos="fade-up" data-aos-delay="100">Register Phone</h1>
                                </div>
                                <div class="col-12 mb-3" id="sender">

                                    <input type="text" id="number" class="form-control" ${success != null ? 'readonly':''} name="phone" value="${phone}" placeholder="Phone">
                                    <c:if test="${success != null}">
                                        <div id="recaptcha-container"></div>
                                        <input type="button" id="send" value="Send" onClick="phoneAuth()">
                                    </c:if>

                                </div>

                                <div class="col-12 mb-3">
                                    <input type="password" id="password" class="form-control" required="" ${success != null ? 'readonly':''} placeholder="Password" value="${password}" name="password">
                                </div>
                                <div class="col-12 mb-3">
                                    <input type="text" class="form-control" id="fullname" required="" ${success != null ? 'readonly':''} placeholder="FullName" value="${name}" name="name">
                                </div>


                                <div id="verifier" style="display: none" class="col-12 mb-3">
                                    <input type="text" class="form-control" style="width: 100%;" id="verificationcode" placeholder="OTP Code">
                                    <input type="button" style="width: 100%;"  id="verify" value="Verify" onClick="codeverify()">
                                    <div class="p-conf" style="display: none">Number Verified Successfully!Go <a href="login">login</a>page</div>
                                    <div class="n-conf" style="display: none">OTP ERROR</div>
                                </div>


                                <script src="https://www.gstatic.com/firebasejs/9.12.1/firebase-app-compat.js"></script>
                                <script src="https://www.gstatic.com/firebasejs/9.12.1/firebase-auth-compat.js"></script>
                                <script src="https://www.gstatic.com/firebasejs/9.12.1/firebase-app-compat.js"></script>
                                <script src="https://www.gstatic.com/firebasejs/9.12.1/firebase-auth-compat.js"></script>
                                <script>

                                            const firebaseConfig = {
                                                apiKey: "AIzaSyA0sYKyGg_Kpw3ve_vS2POnE3fC9vQ8OWc",
                                                authDomain: "sppp-29668.firebaseapp.com",
                                                projectId: "sppp-29668",
                                                storageBucket: "sppp-29668.appspot.com",
                                                messagingSenderId: "513505443172",
                                                appId: "1:513505443172:web:f3c5ade65453affbfb0e71",
                                                measurementId: "G-H1TZE544NC"
                                            };
                                            firebase.initializeApp(firebaseConfig);
                                            render();
                                            function render() {
                                                window.recaptchaVerifier = new firebase.auth.RecaptchaVerifier('recaptcha-container');
                                                recaptchaVerifier.render();
                                            }
                                            function convertPhone(phone) {
                                                if (!phone.startsWith("+84")) {
                                                    phone = phone.replace('0', "+84");
                                                    console.log(phone + "new ");
                                                }
                                                return phone;
                                            }

                                        function phoneAuth() {
                                            var number = convertPhone(document.getElementById('number').value);
                                            firebase.auth().signInWithPhoneNumber(number, window.recaptchaVerifier).then(function (confirmationResult) {
                                                window.confirmationResult = confirmationResult;
                                                coderesult = confirmationResult;
                                                document.getElementById('sender').style.display = 'none';
                                                document.getElementById('verifier').style.display = 'block';
                                            }).catch(function (error) {
                                                alert(error.message);
                                            });
                                        }

                                        function codeverify() {
                                            var code = document.getElementById('verificationcode').value;
                                            coderesult.confirm(code).then(function () {
                                                document.getElementsByClassName('p-conf')[0].style.display = 'block';
                                                document.getElementsByClassName('n-conf')[0].style.display = 'none';
                                                var phone = document.getElementById('number').value;
                                                var pass = document.getElementById('password').value;
                                                var fullname = document.getElementById('fullname').value;
                                                $.ajax({
                                                    url: "addPhone",
                                                    type: "POST",
                                                    data: {phone: phone,
                                                        pass: pass,
                                                        fullname: fullname

                                                    },
                                                    success: function () {
                                                        // Xử lý thành công
                                                        Swal.fire('Thành Công!', 'Đã thêm số điện thoại vào hệ thống').then(() => {

                                                            location.reload();
                                                        });
                                                    },
                                                    error: function (xhr, status, error) {
                                                        // Xử lý lỗi
                                                        console.log("Lỗi:", error);
                                                        Swal.fire('Cảnh báo!', 'Có lỗi xảy ra khi thực hiện thao tác này', 'error');
                                                        // Hiển thị thông báo lỗi hoặc thực hiện các thao tác khác
                                                    }
                                                });

                                            }).catch(function () {
                                                document.getElementsByClassName('p-conf')[0].style.display = 'none';
                                                document.getElementsByClassName('n-conf')[0].style.display = 'block';
                                            });
                                        }
                                </script>
                                <div class="col-12 mb-3">
                                    <span style="color: red">${error}</span>
                                </div>



                                <div class="col-12 mb-3">


                                    <input type="submit" style="padding: 12px 24px; background-color: #4285f4; color: #fff; border: none; border-radius: 4px; cursor: pointer; width: 100%;" ${success != null ? 'disabled':''} value="Register">


                                </div>
                            </div>
                        </form>
                    </div>
                </div>

                <!-- /.untree_co-section -->

                <div class="site-footer">


                    <div class="container">

                        <div class="row">
                            <div class="col-lg-3 mr-auto">
                                <div class="widget">
                                    <h3>About Us<span class="text-primary">.</span> </h3>
                                    <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
                                </div> <!-- /.widget -->
                                <div class="widget">
                                    <h3>Connect</h3>
                                    <ul class="list-unstyled social">
                                        <li><a href="#"><span class="icon-instagram"></span></a></li>
                                        <li><a href="#"><span class="icon-twitter"></span></a></li>
                                        <li><a href="#"><span class="icon-facebook"></span></a></li>
                                        <li><a href="#"><span class="icon-linkedin"></span></a></li>
                                        <li><a href="#"><span class="icon-pinterest"></span></a></li>
                                        <li><a href="#"><span class="icon-dribbble"></span></a></li>
                                    </ul>
                                </div> <!-- /.widget -->
                            </div> <!-- /.col-lg-3 -->

                            <div class="col-lg-2 ml-auto">
                                <div class="widget">
                                    <h3>Projects</h3>
                                    <ul class="list-unstyled float-left links">
                                        <li><a href="#">Web Design</a></li>
                                        <li><a href="#">HTML5</a></li>
                                        <li><a href="#">CSS3</a></li>
                                        <li><a href="#">jQuery</a></li>
                                        <li><a href="#">Bootstrap</a></li>
                                    </ul>
                                </div> <!-- /.widget -->
                            </div> <!-- /.col-lg-3 -->

                            <div class="col-lg-3">
                                <div class="widget">
                                    <h3>Gallery</h3>
                                    <ul class="instafeed instagram-gallery list-unstyled">
                                        <li><a class="instagram-item" href="images/gal_1.jpg" data-fancybox="gal"><img src="images/gal_1.jpg" alt="" width="72" height="72"></a>
                                        </li>
                                        <li><a class="instagram-item" href="images/gal_2.jpg" data-fancybox="gal"><img src="images/gal_2.jpg" alt="" width="72" height="72"></a>
                                        </li>
                                        <li><a class="instagram-item" href="images/gal_3.jpg" data-fancybox="gal"><img src="images/gal_3.jpg" alt="" width="72" height="72"></a>
                                        </li>
                                        <li><a class="instagram-item" href="images/gal_4.jpg" data-fancybox="gal"><img src="images/gal_4.jpg" alt="" width="72" height="72"></a>
                                        </li>
                                        <li><a class="instagram-item" href="images/gal_5.jpg" data-fancybox="gal"><img src="images/gal_5.jpg" alt="" width="72" height="72"></a>
                                        </li>
                                        <li><a class="instagram-item" href="images/gal_6.jpg" data-fancybox="gal"><img src="images/gal_6.jpg" alt="" width="72" height="72"></a>
                                        </li>
                                    </ul>
                                </div> <!-- /.widget -->
                            </div> <!-- /.col-lg-3 -->


                            <div class="col-lg-3">
                                <div class="widget">
                                    <h3>Contact</h3>
                                    <address>43 Raymouth Rd. Baltemoer, London 3910</address>
                                    <ul class="list-unstyled links mb-4">
                                        <li><a href="tel://11234567890">+1(123)-456-7890</a></li>
                                        <li><a href="tel://11234567890">+1(123)-456-7890</a></li>
                                        <li><a href="mailto:info@mydomain.com">info@mydomain.com</a></li>
                                    </ul>
                                </div> <!-- /.widget -->
                            </div> <!-- /.col-lg-3 -->

                        </div> <!-- /.row -->

                        <div class="row mt-5">
                            <div class="col-12 text-center">
                                <p class="copyright">Copyright &copy;<script>document.write(new Date().getFullYear());</script>. All Rights Reserved. &mdash; Designed with love by <a href="https://untree.co">Untree.co</a>  Distributed By <a href="https://themewagon.com">ThemeWagon</a> <!-- License information: https://untree.co/license/ -->
                            </div>
                        </div>
                    </div> <!-- /.container -->
                </div> <!-- /.site-footer -->

                <div id="overlayer"></div>
                <div class="loader">
                    <div class="spinner-border" role="status">
                        <span class="sr-only">Loading...</span>
                    </div>
                </div>

                <script src="js/jquery-3.4.1.min.js"></script>
                <script src="js/popper.min.js"></script>
                <script src="js/bootstrap.min.js"></script>
                <script src="js/owl.carousel.min.js"></script>
                <script src="js/jquery.animateNumber.min.js"></script>
                <script src="js/jquery.waypoints.min.js"></script>
                <script src="js/jquery.fancybox.min.js"></script>
                <script src="js/jquery.sticky.js"></script>
                <script src="js/aos.js"></script>
                <script src="js/custom.js"></script>

                </body>

                </html>

