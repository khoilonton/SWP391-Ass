<%@ page import="DAO.FAQDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.FAQ" %>

<%
    // Đã nhận danh sách FAQ từ request
    List<FAQ> faqList = (List<FAQ>) request.getAttribute("faqList");

%>

<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>FAQs</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f8f9fa;
                padding: 20px;
            }
            .faq-container {
                max-width: 800px;
                margin: auto;
                text-align: left;
            }
            .faq-title {
                font-size: 2rem;
                font-weight: bold;
                margin-bottom: 30px;
            }
            .faq-item {
                background: #fff;
                border-radius: 10px;
                padding: 15px;
                margin-bottom: 10px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
                cursor: pointer;
            }
            .faq-question {
                font-weight: bold;
                font-size: 1.2rem;
                display: flex;
                justify-content: space-between;
                align-items: center;
            }
            .faq-answer {
                display: none;
                margin-top: 10px;
                font-size: 1rem;
                color: #555;
            }
            .faq-icon {
                font-size: 1.5rem;
                color: #6c757d;
            }
            .btn {
                border-radius: 5px;
            }

            .btn-secondary {
                margin-top: 10px;
                margin-left: 100px;
            }
        </style>
    </head>
    <body>

        <div class="faq-container">

            <h2 class="faq-title">Frequently Asked Questions</h2>

            <div id="faqList">
                <% int index = 0; %>
                <% for (FAQ faq : faqList) { %>
                <div class="faq-item" onclick="toggleAnswer('<%= index %>')">
                    <div class="faq-question">
                        <span><%= faq.getQuestion() %></span>
                        <span class="faq-icon">&#9662;</span>
                    </div>
                    <div class="faq-answer" id="answer<%= index %>">
                        <p><%= faq.getAnswer() %></p>
                    </div>
                </div>
                <% index++; %>
                <% } %>
                
            </div>
            <a href="dashBoardCustomer" class="btn btn-secondary">Back</a>
        </div>

        <script>
            function toggleAnswer(id) {
                var answer = document.getElementById('answer' + id);
                answer.style.display = (answer.style.display === 'none' || answer.style.display === '') ? 'block' : 'none';
            }
        </script>
        
    </body>
</html>
