-- 1. Insertion des catégories
INSERT INTO categories (category_type) VALUES
                                           ('TRANSPORT'),
                                           ('ELECTRICITY'),
                                           ('NUTRITION');

-- 2. Insertion des rôles
INSERT INTO roles (authority) VALUES
                                  ('USER_ROLE'),
                                  ('ADMIN_ROLE');

-- 3. Insertion des badges
INSERT INTO badges (label, description) VALUES
                                            ('Eco_Novice', 'Bienvenue dans la team éco ! Chaque geste compte.'),
                                            ('Green_Achiever', 'Bravo ! Tu fais la différence chaque jour.'),
                                            ('Planet_Hero', 'Tu es un modèle pour la planète');

-- 4. Insertion des pays
INSERT INTO countries (name, code_iso) VALUES
                                           ('France', 'FR'),
                                           ('United States', 'US'),
                                           ('Germany', 'DE'),
                                           ('Japan', 'JP'),
                                           ('Canada', 'CA');

-- 5. Insertion des types d'activités
INSERT INTO activity_types (name, unit, category_id) VALUES
                                                         ('Electric Car', 'KM', 1),
                                                         ('Train', 'KM', 1),
                                                         ('Beef Meat', 'KG', 3),
                                                         ('Solar Energy', 'KLW', 2),
                                                         ('Long-haul Flight', 'KM', 1),
                                                         ('Public Transport', 'KM', 1),
                                                         ('Dairy Products', 'KG', 3),
                                                         ('Wind Energy', 'KLW', 2);

-- 6. Insertion des facteurs d'émission
INSERT INTO emission_factors (factor, source, country_id, activity_type_id) VALUES
                                                                                (0.12, 'ADEME 2023', 1, 1),
                                                                                (0.03, 'ADEME 2023', 1, 2),
                                                                                (25.6, 'FAO 2022', 1, 3),
                                                                                (0.08, 'IEA 2023', 1, 4),
                                                                                (0.25, 'EPA 2023', 2, 1),
                                                                                (0.28, 'EPA 2023', 2, 5),
                                                                                (15.2, 'USDA 2023', 2, 3),
                                                                                (0.15, 'IEA 2023', 3, 4);

-- 8. Insertion des utilisateurs
INSERT INTO users (user_name, email, password, role_id, badge_id, country_id) VALUES
                                                                                                ('eco_user', 'user@ecotrack.com', '$2a$10$dummyhash', 1,  1, 1),
                                                                                                ('eco_admin', 'admin@ecotrack.com', '$2a$10$dummyhash', 2,  2, 1),
                                                                                                ('green_ny', 'nyuser@example.com', '$2a$10$dummyhash', 1,  3, 2),
                                                                                                ('berlin_eco', 'berlin@example.com', '$2a$10$dummyhash', 1,  1, 3),
                                                                                                ('tokyo_saver', 'tokyo@example.com', '$2a$10$dummyhash', 1,  2, 4);

-- 9. Insertion des activités utilisateur
INSERT INTO users_activities (quantity, date, sharing_type, nbr_personnes, activity_type_id, user_id) VALUES
                                                                                                                        (50.0, '2023-11-01', 'SOLO', 1, 1,  1),
                                                                                                                        (120.0, '2023-11-02', 'HOUSEHOLD', 4, 2,  2),
                                                                                                                        (2.5, '2023-11-03', 'PRIVATE', 2, 3,  3),
                                                                                                                        (150.0, '2023-11-04', 'PUBLIC', 1, 4,  4),
                                                                                                                        (5000.0, '2023-11-05', 'SOLO', 1, 5,  5);

-- 10. Insertion des scores
INSERT INTO scores (qualitative_score, totalco2, user_id,userActivity_id) VALUES
                                                              ('GOOD', 85.2, 1,1),
                                                              ('EXCELLENT', 42.1, 2,2),
                                                              ('AVERAGE', 120.7, 3,3),
                                                              ('GOOD', 75.3, 4,4),
                                                              ('POOR', 210.5, 5,5);

-- 11. Insertion des notifications
INSERT INTO notifications (date, content, is_read, type, user_id) VALUES
                                                                      ('2023-11-01', 'Your weekly eco report is ready', false, 'REPORT', 1),
                                                                      ('2023-11-02', 'New badge unlocked: Eco Novice!', true, 'BADGE', 2),
                                                                      ('2023-11-03', 'Tip: Reduce your beef consumption', false, 'TIP', 3),
                                                                      ('2023-11-04', 'Your household saved 12kg CO2 this week', true, 'COMPARISON', 4),
                                                                      ('2023-11-05', 'Reminder: log your activities', false, 'REMINDER', 5);

-- 12. Table advice
INSERT INTO advice (id, content, generation_date, type) VALUES
                                                            (1, 'Réduisez votre consommation de viande rouge à 2 fois par semaine', '2023-11-01', 'NUTRITION'),
                                                            (2, 'Utilisez les transports en commun pour vos trajets quotidiens', '2023-11-05', 'TRANSPORT'),
                                                            (3, 'Installez un thermostat programmable pour réguler le chauffage', '2023-11-10', 'ENERGY');

-- 13. Insertion des historiques de conseils
INSERT INTO advice_histories (is_read, is_applied, sent_date, advice_id, user_id) VALUES
                                                                                      (false, false, '2023-11-01', 1, 1),
                                                                                      (true, true, '2023-11-02', 2, 2),
                                                                                      (false, false, '2023-11-03', 3, 3);

-- 14. Table usersGoals
INSERT INTO users_Goals (id, start_date, end_date, goal_achieved, emission_target, user_id) VALUES
                                                                                                (1, '2023-11-01', '2023-12-01', false, 100.0, 1),
                                                                                                (2, '2023-11-01', '2023-11-15', true, 50.0, 2),
                                                                                                (3, '2023-10-01', '2023-11-01', false, 200.0, 3);

-- 15. Table usersReviews
INSERT INTO users_Reviews (id, rating, comment, createdAT, user_id) VALUES
                                                                        (1, 5, 'Conseils très utiles pour réduire mon empreinte carbone', '2023-11-02', 1),
                                                                        (2, 4, 'Objectifs clairs et réalisables', '2023-11-06', 2),
                                                                        (3, 3, 'Difficile à mettre en œuvre dans mon cas particulier', '2023-11-11', 3);