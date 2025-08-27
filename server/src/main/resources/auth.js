// Animation des formulaires
function showLogin() {
    document.getElementById('login-form').style.display = 'block';
    document.getElementById('register-form').style.display = 'none';
    document.querySelectorAll('.eco-tab').forEach(tab => tab.classList.remove('active'));
    document.querySelectorAll('.eco-tab')[0].classList.add('active');
}

function showRegister() {
    document.getElementById('login-form').style.display = 'none';
    document.getElementById('register-form').style.display = 'block';
    document.querySelectorAll('.eco-tab').forEach(tab => tab.classList.remove('active'));
    document.querySelectorAll('.eco-tab')[1].classList.add('active');
}

// Gestion de la connexion avec animation
document.getElementById('loginForm').addEventListener('submit', async function(e) {
    e.preventDefault();

    const btn = e.target.querySelector('button');
    btn.disabled = true;
    btn.innerHTML = '<span>Connexion en cours...</span>';

    const username = document.getElementById('login-username').value;
    const password = document.getElementById('login-password').value;

    try {
        const response = await fetch('/api/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Basic ' + btoa(username + ":" + password)
            }
        });

        if (response.ok) {
            const token = await response.text();
            localStorage.setItem('jwtToken', token);

            // Animation de succès
            btn.innerHTML = '<span>Connexion réussie!</span>';
            await new Promise(resolve => setTimeout(resolve, 1000));

            window.location.href = '/dashboard.html';
        } else {
            btn.disabled = false;
            btn.innerHTML = '<span>Se connecter</span><i class="icon-arrow-right"></i>';
            showError('Identifiants incorrects');
        }
    } catch (error) {
        console.error('Erreur:', error);
        btn.disabled = false;
        btn.innerHTML = '<span>Se connecter</span><i class="icon-arrow-right"></i>';
        showError('Erreur de connexion');
    }
});

// Gestion de l'inscription avec feedback visuel
document.getElementById('registerForm').addEventListener('submit', async function(e) {
    e.preventDefault();

    const btn = e.target.querySelector('button');
    btn.disabled = true;
    btn.innerHTML = '<span>Création du compte...</span>';

    const userData = {
        email: document.getElementById('register-email').value,
        userName: document.getElementById('register-username').value,
        password: document.getElementById('register-password').value,
        householdId: parseInt(document.getElementById('register-household').value),
        countryId: parseInt(document.getElementById('register-country').value)
    };

    try {
        const response = await fetch('/api/auth/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userData)
        });

        if (response.ok) {
            btn.innerHTML = '<span>Compte créé!</span><i class="icon-leaf"></i>';
            await new Promise(resolve => setTimeout(resolve, 1500));
            showLogin();

            // Réinitialiser le formulaire
            e.target.reset();
        } else {
            const error = await response.json();
            btn.disabled = false;
            btn.innerHTML = '<span>Commencer le suivi</span><i class="icon-leaf"></i>';
            showError(error.message || 'Erreur lors de l\'inscription');
        }
    } catch (error) {
        console.error('Erreur:', error);
        btn.disabled = false;
        btn.innerHTML = '<span>Commencer le suivi</span><i class="icon-leaf"></i>';
        showError('Erreur de connexion au serveur');
    }
});

// Fonction pour afficher les erreurs
function showError(message) {
    const errorEl = document.createElement('div');
    errorEl.className = 'eco-error-message';
    errorEl.textContent = message;

    const forms = document.querySelectorAll('.eco-form-container');
    forms.forEach(form => {
        const existingError = form.querySelector('.eco-error-message');
        if (existingError) existingError.remove();

        form.prepend(errorEl);
        setTimeout(() => errorEl.remove(), 5000);
    });
}

// Style dynamique pour les erreurs (à ajouter dans le CSS)
document.head.insertAdjacentHTML('beforeend', `
    <style>
        .eco-error-message {
            padding: 1rem;
            margin-bottom: 1.5rem;
            background: #ffecec;
            color: #e74c3c;
            border-radius: 8px;
            border-left: 4px solid #e74c3c;
            animation: fadeIn 0.3s ease;
        }
        
        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(-10px); }
            to { opacity: 1; transform: translateY(0); }
        }
    </style>
`);

// Afficher le formulaire de connexion par défaut
showLogin();