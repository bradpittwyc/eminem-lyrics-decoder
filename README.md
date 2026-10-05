# 🎤 Eminem Lyrics Multidimensional Decoder (阿姆全专辑多维歌词解构宇宙)

<div align="center">

![Eminem Discography 1996-2024](https://img.shields.io/badge/Discography-1996--2024%20(12%20Albums)-red?style=for-the-badge)
![Tracks Covered](https://img.shields.io/badge/Tracks-219%20Songs-zinc?style=for-the-badge)
![AI Powered](https://img.shields.io/badge/LLM-Gemini%20%7C%20DeepSeek%20%7C%20OpenAI-purple?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)

**专为 Eminem (Slim Shady) 音乐狂热爱好者、嘻哈制作人与修辞学研究者打造的现代全景歌词多维解构平台。**

[在线体验 (GitHub Pages)](#) · [功能特性](#-功能特性) · [音乐学算法原理](#-音乐学算法原理-fam-abc) · [快速上手](#-快速上手) · [LLM 深度引擎配置](#-llm-深度透视配置)

</div>

---

## ✨ 功能特性

### 1. 💿 官方正规大碟全谱系覆盖 (1996 - 2024)
- **12 张录音室专辑** 100% 完整收录：从底特律地下时期的《Infinite》(1996) 到封神三部曲《The Slim Shady LP》《The Marshall Mathers LP》《The Eminem Show》，直至 2024 概念大碟《The Death of Slim Shady (Coup De Grâce)》。
- **219 首正规录音室音轨全量检索**：支持专辑时间线滚动条、快捷曲目抽屉与按需即时切换。

### 2. 🌐 公开曲库原生直连拉取 (Native Open Lyrics API)
- 独创级联拉取引擎（Cascade Lyrics Engine）：
  - **第一级**：直连全球开放无跨域限制的 `Lyrics.ovh` 开放歌词库，毫秒级加载整首完整歌词；
  - **第二级**：备选 `LRCLIB` 开源歌词中心；
  - **第三级**：支持调用已配置的 LLM（Gemini / DeepSeek）精准提取；
  - **第四级**：直达 [eminem.com/lyrics](https://www.eminem.com/lyrics/) 官方曲目页，支持一键剪贴板秒速全解构。

### 3. 🔬 音素韵核提取与韵脚色彩家族 (Phonetic Clustering)
- **多音节韵脚色彩聚类**：自动将尾韵及行内核心元音归并至 **Fam A (红)**、**Fam B (蓝)**、**Fam C (绿)**。
- **发音扭曲感知 (Rhyme Bending)**：智能识别阿姆标志性的发音扭曲（Slant Rhymes / Assonance），用特殊橙色虚线高亮标注。
- **音节流速 (SPS) 与 Flow 节奏**：估算单行音节负荷（Syllables per bar）与发音流速（如常态 4.5 SPS 到极限 8.5+ SPS 机关枪快嘴）。

### 4. 🎭 三重人格声部谱系仪 (Persona Spectrum)
实时分析并量化阿姆在不同创作阶段的三大声部交锋：
- **Slim Shady (恶搞/挑衅/极端讽刺)**：暴力美学、黑色幽默与荒诞漫画意象；
- **Marshall Mathers (真实自我/痛苦忏悔)**：家庭创伤、内疚自省与现实绝望；
- **Eminem / Rap God (纯粹技术/极速对决)**：押韵机器、文字游戏与嘻哈竞技神格化。

### 5. 🧠 LLM 专家级深度语义透视 (Gemini / OpenAI API 直连)
在浏览器本地直连 Google Gemini API 或 OpenAI 兼容格式（如 DeepSeek、Groq、Ollama）：
- **Layer 1 (表层叙事)**：语法结构与白话精译；
- **Layer 2 (多重双关与修辞)**：深度拆解一语双关（Double Entendre）、多重同音异义梗（Homophones）与隐藏内韵；
- **Layer 3 (文化考据与历史 Beef)**：挖掘被 Diss 对象、历史恩怨、流行文化暗号与阿姆宇宙互文。
- **持久化本地缓存**：解析结果自动保存在本地，零延迟秒开且不浪费 Token。

---

## 🎵 音乐学算法原理：Fam A / B / C 是什么？

在专业嘻哈音乐学（Hip-Hop Prosody）中，**“Fam” 是 “Rhyme Family”（韵脚家族 / 音素韵族）的缩写**。

像 Eminem 这样的技术流大师，押韵从来不是简单的单词末尾对齐，而是**多音节元音音素群（Vowel Sound Clusters）的交织编排**：

```
[Fam A (红)] ─── 最先确立的复合元音主韵（如 /æ - ə - l/）
[Fam B (蓝)] ─── 穿插引入的第二辅助韵族（如 /aɪ - t/）
[Fam C (绿)] ─── 跨行交错的第三嵌套韵族（如 /eɪ - ʃən/）
[Bending(橙)] ── 强行扭曲发音造押（如将 orange 掰成 door-hinge）
```

通过这套视觉色彩系统，整首歌词就像呈现了一张**押韵热成像图**，让错综复杂的跨小节内韵与多音节连环押跃然纸上。

---

## 🚀 快速上手

本项目为**纯前端单页架构（Zero-Dependency Static App）**，无需安装 Node.js、无需编译，开箱即用。

### 方式一：直接在本地运行
1. 克隆本仓库：
   ```bash
   git clone https://github.com/bradpittwyc/eminem-lyrics-decoder.git
   cd eminem-lyrics-decoder
   ```
2. 直接双击 `index.html`，即可在任何现代浏览器（Chrome、Edge、Safari、Firefox）中畅享全部功能！

### 方式二：部署至 GitHub Pages
1. 在 GitHub 仓库设置中的 **Pages** 选项卡；
2. Source 选择 `Deploy from a branch` -> `main` / `root`；
3. 保存后即可获得全球 CDN 加速的在线访问网址。

---

## ⚙️ LLM 深度透视配置

想要开启大模型的多重双关与历史梗透视：
1. 点击页面顶部的 **`[⚙️ LLM 引擎]`** 按钮；
2. 选择提供商：
   - **Google Gemini API**（推荐 `gemini-2.5-flash`，长上下文响应迅速）；
   - **OpenAI 兼容协议**（支持 DeepSeek `deepseek-chat`、Groq、本地 Ollama 等）；
3. 填入你的 API Key，点击 **「测试连通性」** 并保存；
4. 点击任意歌词行右下方的 **`[✨ 调用 LLM 深度透视此句]`** 即可享受专家级剖析！

> 🔒 **隐私保证**：你的 API Key 和所有分析缓存仅保存在你本机的浏览器 `localStorage` 中，绝不向任何第三方后端上传。

---

## 📂 项目结构

```
eminem-lyrics-decoder/
├── index.html         # 核心交互应用 (内置全专元数据、音素引擎、LLM驱动器与Tailwind UI)
├── catalog.json       # 12 张专辑 219 首官方录音室音轨元数据映射
├── README.md          # 项目说明文档与音乐学原理指南
└── LICENSE            # MIT 开源许可证
```

---

## 📜 版权与鸣谢
- 歌词文本通过公开网络接口实时拉取或由用户自主导入分析，版权归原作者 Marshall Mathers (Eminem)、Aftermath Entertainment、Interscope Records 及 Shady Records 所有；
- 官方歌词中心：[eminem.com/lyrics](https://www.eminem.com/lyrics/)；
- 遵循 [MIT License](LICENSE) 开源。
