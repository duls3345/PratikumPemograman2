package dao;

import java.util.List;
import model.Member;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class MemberDao {
    private final SqlSessionFactory sqlSessionFactory;

    // Constructor untuk inisialisasi SqlSessionFactory
    public MemberDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    // Insert data Member ke database
    public int insert(Member member) {
        int result;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            result = session.insert("mapper.MemberMapper.insert", member);
            session.commit(); // Perlu commit untuk menyimpan perubahan
        }
        return result;
    }

    // Update data Member di database
    public int update(Member member) {
        int result;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            result = session.update("mapper.MemberMapper.update", member);
            session.commit(); // Perlu commit untuk menyimpan perubahan
        }
        return result;
    }

    // Delete data Member dari database menggunakan ID
    public int delete(int id) {
        int result;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            result = session.delete("mapper.MemberMapper.delete", id); // Menggunakan ID langsung
            session.commit(); // Perlu commit untuk menyimpan perubahan
        }
        return result;
    }

    // Ambil semua data Member dari database
    public List<Member> findAll() {
        List<Member> result;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            result = session.selectList("mapper.MemberMapper.findAll");
        }
        return result;
    }
}
