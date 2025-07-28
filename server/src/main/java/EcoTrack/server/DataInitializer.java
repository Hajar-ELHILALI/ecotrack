package EcoTrack.server;

import EcoTrack.server.entity.Badge;
import EcoTrack.server.entity.Category;
import EcoTrack.server.entity.Role;
import EcoTrack.server.enums.BadgeLabel;
import EcoTrack.server.enums.CategoryType;
import EcoTrack.server.repository.BadgeRepository;
import EcoTrack.server.repository.CategoryRepository;
import EcoTrack.server.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final CategoryRepository categoryRepository;
    private final RoleRepository roleRepository;
    private final BadgeRepository badgeRepository;

    public DataInitializer(CategoryRepository categoryRepository, RoleRepository roleRepository, BadgeRepository badgeRepository) {
        this.categoryRepository = categoryRepository;
        this.roleRepository = roleRepository;
        this.badgeRepository = badgeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {
            // Creating the default categories
            Category transport =new Category();
            transport.setCategoryType(CategoryType.TRANSPORT);

            Category electricity =new Category();
            electricity.setCategoryType(CategoryType.ELECTRICITY);

            Category nutrition =new Category();
            nutrition.setCategoryType(CategoryType.NUTRITION);


            categoryRepository.save(transport);
            categoryRepository.save(electricity);
            categoryRepository.save(nutrition);
        }

        if(roleRepository.count() == 0){
            // Creating the roles
            Role role =new Role();
            role.setAuthority("USER_ROLE");

            Role admin =new Role();
            admin.setAuthority("ADMIN_ROLE");

            roleRepository.save(role);
            roleRepository.save(admin);
        }

        if(badgeRepository.count() == 0){
            // Creating the default badges
            Badge badge1 =new Badge();
            badge1.setDescription("Bienvenue dans la team éco ! Chaque geste compte.");
            badge1.setLabel(BadgeLabel.Eco_Novice);

            Badge badge2 =new Badge();
            badge2.setDescription("Bravo ! Tu fais la différence chaque jour.");
            badge2.setLabel(BadgeLabel.Green_Achiever);

            Badge badge3 =new Badge();
            badge3.setDescription("Tu es un modèle pour la planète");
            badge3.setLabel(BadgeLabel.Planet_Hero);

            badgeRepository.save(badge1);
            badgeRepository.save(badge2);
            badgeRepository.save(badge3);
        }
    }
}

