

## 1. AI Resume Screening System

### Cloud Deployment & Service Model

- Use Public Cloud (like AWS, Azure, or GCP) because it is cost-effective and scalable.  
- Use PaaS (Platform as a Service) for easy development and deployment.  
- For AI models, use IaaS (GPU-based instances) when heavy computation is required.  

---

### AI Processing Approach

- Use Machine Learning models to analyze resumes.  
- Apply NLP (Natural Language Processing) to extract skills, experience, and keywords.  
- Rank candidates based on job requirements using scoring algorithms.  
- Automate interview scheduling using AI-based decision systems.  

---

### Storage and Processing Pipeline

- Store resumes in cloud storage (like S3 / Blob Storage).  
- Use a database (MongoDB / SQL) to store candidate details.  

**Processing Steps:**
1. Upload resume  
2. Extract data using AI/NLP  
3. Store structured data  
4. Rank candidates  
5. Display results  

---

### Key Design Considerations (Focus)

- **Serverless vs Containers:**  
  - Serverless for low cost and automatic scaling  
  - Containers for better control and heavy workloads  

- **Batch vs Real-time Processing:**  
  - Batch for large data processing  
  - Real-time for instant resume screening  

- **GPU Optimization:**  
  - Use GPU instances for faster AI model training and prediction  

- **Data Security:**  
  - Encrypt sensitive data  
  - Use authentication and access control  

---

### Conclusion

This system uses cloud computing, AI, and scalable architecture to efficiently screen, rank, and manage resumes while maintaining performance and security.

---
