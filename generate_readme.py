import os
import re

# Root folder is current folder (LeetcodeSolutions)
ROOT = "."
RANGES = ["0001-1000", "1001-2000", "2001-3000", "3001-4000"]

def parse_filename(filename):
    """Extract problem number and title from filename like 123_two_sum.java"""
    match = re.match(r"(\d+)_(.*)\.java", filename)
    if match:
        number = int(match.group(1))
        title = match.group(2).replace("_", " ").title()
        return number, title
    return None, None

def generate_table(folder):
    """Generate markdown table for one folder"""
    path = os.path.join(ROOT, folder)
    if not os.path.exists(path):
        return f"*(No problems added yet in {folder})*"

    rows = ["| # | Title | Solution |",
            "|---|-------|----------|"]

    for file in sorted(os.listdir(path)):
        if file.endswith(".java"):
            num, title = parse_filename(file)
            if num:
                file_path = f"{folder}/{file}"
                rows.append(f"| {num} | {title} | [Java]({file_path}) |")

    return "\n".join(rows)

def main():
    readme_lines = [
        "# LeetCode Solutions (Number-wise)\n",
        "This repository contains my solutions to LeetCode problems, organized **by problem number**.\n",
        "---\n",
        "# Watch Explainations\n",
        "Checkout the full walkthrough and solution discussion in Hindi on my [YouTube Channel](https://www.youtube.com/@kernel-queen).\n",
        "---\n",
        "## 📂 Quick Navigation",
    ]

    # Add quick navigation
    for folder in RANGES:
        readme_lines.append(f"- [{folder}](#{folder})")
    readme_lines.append("\n---\n")

    # Add each folder section
    for folder in RANGES:
        readme_lines.append(f"## {folder}\n")
        readme_lines.append(generate_table(folder))
        readme_lines.append(f"\n\n[🔼 Back to Top](#leetcode-solutions-number-wise)\n")
        readme_lines.append("---\n")

    # Save README at root
    with open("README.md", "w", encoding="utf-8") as f:
        f.write("\n".join(readme_lines))

    print("✅ README.md generated successfully!")

if __name__ == "__main__":
    main()
