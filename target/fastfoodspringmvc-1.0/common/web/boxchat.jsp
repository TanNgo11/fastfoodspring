
<%@ page import="com.fastfood.utils.SecurityUtils"%>

<security:authorize access="isAuthenticated()">
	<input id="chat-userId" type=hidden
		value="<%=SecurityUtils.getPrincipal().getId()%>" />
</security:authorize>
<security:authorize access="isAnonymous()">
	<input id="chat-userId" type=hidden value="" />
</security:authorize>
<div class="floating-chat">
	<span class="chat-notification hidden"></span> <i
		class="fa fa-comments" aria-hidden="true"></i>
	<div class="chat">
		<div id="overlay">
			<div id="text"><a href="/login">Please log in to chat</a></div>
		</div>
		<div style="background-color: #0084ff;">
			<div class="header-chat" id="chat-page">
				<span style="color: white;" class="title-chat">Admin</span>
				<button>
					<i style="font-size: 22px; color: white;" class="fa fa-times"
						aria-hidden="true"></i>
				</button>

			</div>
		</div>

		<span
			style="border-top: 1px; border-style: solid; border-top-color: black"></span>
		<ul class="messages" id="chat-messages">


		</ul>
		<div class="footer-chat" id="messageForm">
			<input style="outline: none; border: none" autocomplete="off"
				type="text" id="messageInput" class="text-box">
			<button id="sendMessage">
				<svg style="width: 24px; height: 24px" viewBox="0 0 24 24">
          <path fill="#006ae3" d="M2,21L23,12L2,3V10L17,12L2,14V21Z"></path>
        </svg>
			</button>
		</div>
	</div>
</div>

