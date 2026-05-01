
## 2. OTT Streaming Platform

### Scenario

Video platform with HD/4K streaming, recommendations, multi-device access, subscription model.

---

### Constraints

- High bandwidth  
- Global scalability  
- Zero buffering expectation  
- Large storage  

---

### Cloud Model Selection

- Use Public Cloud (AWS, Azure, GCP) for global reach  
- Use IaaS for storage and servers  
- Use PaaS for application deployment  
- Use CDN (Content Delivery Network) for fast video delivery  

---

### Video Delivery System Design

- Store videos in distributed cloud storage  
- Use CDN & edge servers to deliver content from nearest location  
- Apply video compression for HD/4K streaming  
- Use adaptive streaming (adjust quality based on internet speed)  
- Ensure low latency to avoid buffering  

---

### Global Scaling Plan

- Use auto-scaling to handle high traffic  
- Deploy services in multiple regions worldwide  
- Use load balancers to distribute traffic  
- Use caching to improve performance  

---

### Key Focus Areas

- **CDN & Edge Delivery:** Faster streaming with low delay  
- **Distributed Storage:** Store large video data across multiple servers  
- **Auto-scaling:** Automatically increase/decrease resources  
- **Recommendation System:** Suggest content using AI/ML  

---

