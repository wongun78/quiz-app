const isAuthenticated = true;

document.addEventListener("DOMContentLoaded", function () {
  const authUI = document.querySelectorAll("[data-auth-ui]");
  const guestUI = document.querySelectorAll("[data-guest-ui]");
  const authRequiredLinks = document.querySelectorAll("[data-auth-required]");

  if (isAuthenticated) {
    authUI.forEach((el) => (el.style.display = ""));
    guestUI.forEach((el) => (el.style.display = "none"));
    authRequiredLinks.forEach((el) => (el.style.display = ""));
  } else {
    authUI.forEach((el) => (el.style.display = "none"));
    guestUI.forEach((el) => (el.style.display = ""));
    authRequiredLinks.forEach((el) => (el.style.display = "none"));
  }

  const mobileMenuToggle = document.getElementById("mobileMenuToggle");
  const mobileMenu = document.getElementById("mobileMenu");
  const userAvatarBtn = document.getElementById("userAvatarBtn");
  const userDropdownMenu = document.getElementById("userDropdownMenu");

  if (mobileMenuToggle && mobileMenu) {
    mobileMenuToggle.addEventListener("click", function () {
      mobileMenu.classList.toggle("active");
    });

    document.addEventListener("click", function (event) {
      const isClickInsideMenu = mobileMenu.contains(event.target);
      const isClickOnToggle = mobileMenuToggle.contains(event.target);

      if (
        !isClickInsideMenu &&
        !isClickOnToggle &&
        mobileMenu.classList.contains("active")
      ) {
        mobileMenu.classList.remove("active");
      }
    });

    window.addEventListener("resize", function () {
      if (window.innerWidth >= 768) {
        mobileMenu.classList.remove("active");
      }
    });
  }

  if (userAvatarBtn && userDropdownMenu) {
    userAvatarBtn.addEventListener("click", function (e) {
      e.stopPropagation();
      userDropdownMenu.classList.toggle("active");
    });

    document.addEventListener("click", function (event) {
      if (
        !userDropdownMenu.contains(event.target) &&
        !userAvatarBtn.contains(event.target)
      ) {
        userDropdownMenu.classList.remove("active");
      }
    });
  }
  /* Shared small-form helpers (used by contact, login, register) */
  function validateEmail(value) {
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(value);
  }

  function clearFormErrors(form) {
    form.querySelectorAll(".input-error").forEach((el) => el.remove());
    form
      .querySelectorAll(".input-invalid")
      .forEach((el) => el.classList.remove("input-invalid"));
    const success = form.querySelector(
      ".form-success, .contact-success, .auth-success"
    );
    if (success) success.remove();
  }

  function showError(input, message) {
    const err = document.createElement("div");
    err.className = "input-error";
    err.textContent = message;
    input.classList.add("input-invalid");
    input.parentNode.appendChild(err);
  }

  const contactForm = document.getElementById("contactForm");
  if (contactForm) {
    contactForm.addEventListener("submit", function (e) {
      e.preventDefault();
      clearFormErrors(contactForm);

      const name = contactForm.querySelector("#name");
      const email = contactForm.querySelector("#email");
      const message = contactForm.querySelector("#message");

      let firstInvalid = null;
      let hasError = false;

      if (!name.value.trim()) {
        showError(name, "Name is required.");
        firstInvalid = firstInvalid || name;
        hasError = true;
      }

      if (!email.value.trim() || !validateEmail(email.value.trim())) {
        showError(email, "Please enter a valid email.");
        firstInvalid = firstInvalid || email;
        hasError = true;
      }

      if (!message.value.trim() || message.value.trim().length < 10) {
        showError(message, "Message must be at least 10 characters.");
        firstInvalid = firstInvalid || message;
        hasError = true;
      }

      if (hasError) {
        firstInvalid.focus();
        firstInvalid.scrollIntoView({ behavior: "smooth", block: "center" });
        return;
      }

      const success = document.createElement("div");
      success.className = "contact-success";
      success.textContent = "Your message has been sent. Thank you!";
      contactForm.appendChild(success);
      contactForm.reset();
      setTimeout(() => success.remove(), 4000);
    });
  }

  const loginForm = document.getElementById("loginForm");
  if (loginForm) {
    loginForm.addEventListener("submit", function (e) {
      e.preventDefault();
      clearFormErrors(loginForm);

      const username = loginForm.querySelector("#username");
      const password = loginForm.querySelector("#password");
      let firstInvalid = null;
      let hasError = false;

      if (!username.value.trim()) {
        showError(username, "Username is required.");
        firstInvalid = firstInvalid || username;
        hasError = true;
      }

      if (!password.value.trim() || password.value.trim().length < 6) {
        showError(password, "Password must be at least 6 characters.");
        firstInvalid = firstInvalid || password;
        hasError = true;
      }

      if (hasError) {
        firstInvalid.focus();
        return;
      }

      const success = document.createElement("div");
      success.className = "auth-success";
      success.textContent = "Login successful.";
      loginForm.appendChild(success);
      setTimeout(() => success.remove(), 3000);
    });
  }

  const registerForm = document.getElementById("registerForm");
  if (registerForm) {
    registerForm.addEventListener("submit", function (e) {
      e.preventDefault();
      clearFormErrors(registerForm);

      const firstName = registerForm.querySelector("#firstName");
      const lastName = registerForm.querySelector("#lastName");
      const email = registerForm.querySelector("#email");
      const username = registerForm.querySelector("#username");
      const phone = registerForm.querySelector("#phone");
      const password = registerForm.querySelector("#password");
      const confirmPassword = registerForm.querySelector("#confirmPassword");

      let firstInvalid = null;
      let hasError = false;

      if (!firstName.value.trim()) {
        showError(firstName, "First name is required.");
        firstInvalid = firstInvalid || firstName;
        hasError = true;
      }

      if (!lastName.value.trim()) {
        showError(lastName, "Last name is required.");
        firstInvalid = firstInvalid || lastName;
        hasError = true;
      }

      if (!email.value.trim() || !validateEmail(email.value.trim())) {
        showError(email, "Please enter a valid email.");
        firstInvalid = firstInvalid || email;
        hasError = true;
      }

      if (!username.value.trim()) {
        showError(username, "Username is required.");
        firstInvalid = firstInvalid || username;
        hasError = true;
      }

      if (
        phone.value.trim() &&
        !/^\+?[0-9\s\-()]{7,20}$/.test(phone.value.trim())
      ) {
        showError(phone, "Please enter a valid phone number.");
        firstInvalid = firstInvalid || phone;
        hasError = true;
      }

      if (!password.value.trim() || password.value.trim().length < 8) {
        showError(password, "Password must be at least 8 characters.");
        firstInvalid = firstInvalid || password;
        hasError = true;
      }

      if (confirmPassword.value.trim() !== password.value.trim()) {
        showError(confirmPassword, "Passwords do not match.");
        firstInvalid = firstInvalid || confirmPassword;
        hasError = true;
      }

      if (hasError) {
        firstInvalid.focus();
        firstInvalid.scrollIntoView({ behavior: "smooth", block: "center" });
        return;
      }

      const success = document.createElement("div");
      success.className = "auth-success";
      success.textContent = "Registration successful.";
      registerForm.appendChild(success);
      registerForm.reset();
      setTimeout(() => success.remove(), 3000);
    });
  }
});

function setActiveNavLink() {
  const currentPage = window.location.pathname.split("/").pop() || "home.html";
  const navLinks = document.querySelectorAll(".navbar-link, .mobile-menu-link");

  navLinks.forEach((link) => {
    link.classList.remove("active");
    const href = link.getAttribute("href");
    if (href === currentPage) {
      link.classList.add("active");
    }
  });
}

document.querySelectorAll('a[href^="#"]').forEach((anchor) => {
  anchor.addEventListener("click", function (e) {
    e.preventDefault();
    const target = document.querySelector(this.getAttribute("href"));
    if (target) {
      target.scrollIntoView({
        behavior: "smooth",
        block: "start",
      });
    }
  });
});

setActiveNavLink();
