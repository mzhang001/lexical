import sys

sys.path.append('/auto/homes/mz342/github/pyyaml/lib')
sys.path.append('/auto/homes/mz342/github/nltk/')
import yaml
import nltk

sentence = """Ha ha ha, hahaha"""
tokens = nltk.word_tokenize(sentence)
tokens

tagged = nltk.pos_tag(tokens)
tagged[0:6]
