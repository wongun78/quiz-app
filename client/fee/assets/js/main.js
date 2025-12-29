const isAuthenticated = true;

document.addEventListener("DOMContentLoaded", function () {
  const authUI = document.querySelectorAll("[data-auth-ui]");
  const guestUI = document.querySelectorAll("[data-guest-ui]");
  const authRequiredLinks = document.querySelectorAll("[data-auth-required]");

  if (isAuthenticated) {
    authUI.forEach((el) => el.classList.remove("hidden"));
    guestUI.forEach((el) => el.classList.add("hidden"));
    authRequiredLinks.forEach((el) => el.classList.remove("hidden"));
  } else {
    authUI.forEach((el) => el.classList.add("hidden"));
    guestUI.forEach((el) => el.classList.remove("hidden"));
    authRequiredLinks.forEach((el) => el.classList.add("hidden"));
  }

  const mobileMenuToggle = document.getElementById("mobileMenuToggle");
  const mobileMenu = document.getElementById("mobileMenu");
  const userAvatarBtn = document.getElementById("userAvatarBtn");
  const userDropdownMenu = document.getElementById("userDropdownMenu");

  if (mobileMenuToggle && mobileMenu) {
    mobileMenuToggle.addEventListener("click", function () {
      mobileMenu.classList.toggle("hidden");
    });

    document.addEventListener("click", function (event) {
      const isClickInsideMenu = mobileMenu.contains(event.target);
      const isClickOnToggle = mobileMenuToggle.contains(event.target);

      if (
        !isClickInsideMenu &&
        !isClickOnToggle &&
        !mobileMenu.classList.contains("hidden")
      ) {
        mobileMenu.classList.add("hidden");
      }
    });

    window.addEventListener("resize", function () {
      if (window.innerWidth >= 768) {
        mobileMenu.classList.add("hidden");
      }
    });
  }

  if (userAvatarBtn && userDropdownMenu) {
    userAvatarBtn.addEventListener("click", function (e) {
      e.stopPropagation();
      userDropdownMenu.classList.toggle("hidden");
    });

    document.addEventListener("click", function (event) {
      if (
        !userDropdownMenu.contains(event.target) &&
        !userAvatarBtn.contains(event.target)
      ) {
        userDropdownMenu.classList.add("hidden");
      }
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
