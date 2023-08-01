package deleted;

import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private UserRoleRepository userRoleRepository;

    @Override
    public void save(UserRole userRole) {
        this.userRoleRepository.save(userRole);
    }
}
