## 1. INTRODUCTION
ChatterBox-AI-HELPER is an intelligent question-and-answer system created to help users resolve common issues and answer general questions. It aims to enhance the efficiency of providing responses and decrease the time investment required for dealing with frequently encountered problems. This tool leverages artificial intelligence to deliver quick, accurate, and effective solutions, helping users get answers faster and reducing the workload for support teams or individuals.

## 2. TECH STACK

   SpringBoot
   DDD architecture (Domain-Driven Design)
   GitHub repository usage
   API web scraping
   AI API integration
   Scheduled tasks
   Image packaging
   Docker container deployment

## 3. Copyright
This project is licensed under the Apache License 2.0. It is created for educational purposes and is prohibited from being sold in any paid form by training institutions, private individuals, or corporate organizations.

## 4. Train Your Own Model
### 4.1 Environment Installation
* Download Python [download python](https://www.python.org/downloads/macos/) (3.6 version or above)
* Configure Python
* * Search the path `which python3`
  * Environment configuration `alias python="/Library/Frameworks/Python.framework/Versions/3.10/bin/python3"`
  * Activate the configuration `source .bash_profile`
* Download pip `curl https://bootstrap.pypa.io/get-pip.py | python3`

### 5.2 tensorflow
  URL: [link](https://www.tensorflow.org/install?hl=zh-cn)
  
  SCRIPT：
  ```
  # Requires the latest pip
  pip install --upgrade pip

  # Current stable release for CPU and GPU
  pip install tensorflow

  # Or try the preview build (unstable)
  pip install tf-nightly
  ```
  mac m1: `python3 -m pip install tensorflow-macos`
  Test: 
```
python3 -c "import tensorflow as tf; print(tf.reduce_sum(tf.random.normal([1000, 1000])))"

# 结果；tf.Tensor(228.22836, shape=(), dtype=float32)
```
  
