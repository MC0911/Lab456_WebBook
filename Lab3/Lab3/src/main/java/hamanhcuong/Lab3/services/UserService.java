package hamanhcuong.Lab3.services;

import hamanhcuong.Lab3.entity.User;
import hamanhcuong.Lab3.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;
    public void save (User user)
    {
        userRepository.save(user);
    }
}
