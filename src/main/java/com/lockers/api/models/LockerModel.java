    package com.lockers.api.models;

    import com.lockers.api.misc.LockerId;
    import jakarta.persistence.*;
    import org.hibernate.annotations.CreationTimestamp;
    import org.hibernate.annotations.UpdateTimestamp;

    import java.time.Instant;

    @Entity
    @Table(name = "lockers", uniqueConstraints = @UniqueConstraint(columnNames = {"number", "sector"}))
    public class LockerModel {
        @EmbeddedId
        private LockerId id;

        @CreationTimestamp
        private Instant createdAt;

        @UpdateTimestamp
        private Instant updatedAt;

        @ManyToOne
        @JoinColumn(name = "student_grr", nullable = false)
        private StudentModel student;

        public LockerId getId() {
            return id;
        }

        public void setId(LockerId id) {
            this.id = id;
        }

        public Instant getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Instant createdAt) {
            this.createdAt = createdAt;
        }

        public Instant getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(Instant updatedAt) {
            this.updatedAt = updatedAt;
        }

        public StudentModel getStudent() {
            return student;
        }

        public void setStudent(StudentModel student) {
            this.student = student;
        }

    }
