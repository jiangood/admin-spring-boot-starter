// @ts-check
// `@type` JSDoc annotations allow editor autocompletion and type checking
// (when paired with `@ts-check`).
// There are various equivalent ways to declare your Docusaurus config.
// See: https://docusaurus.io/docs/api/docusaurus-config

import {themes as prismThemes} from 'prism-react-renderer';

// This runs in Node.js - Don't use client-side code here (browser APIs, JSX...)

/** @type {import('@docusaurus/types').Config} */
const config = {
  title: 'open-admin',
  tagline: '小型管理系统框架，提供一整套前后端开箱即用的解决方案',

  // Future flags, see https://docusaurus.io/docs/api/docusaurus-config#future
  future: {
    v4: true, // Improve compatibility with the upcoming Docusaurus v4
  },

  // Set the production url of your site here
  url: 'https://jiangood.github.io',
  // Set the /<baseUrl>/ pathname under which your site is served
  // For GitHub pages deployment, it is often '/<projectName>/'
  baseUrl: '/open-admin/',

  // GitHub pages deployment config.
  // If you aren't using GitHub pages, you don't need these.
  organizationName: 'jiangood', // Usually your GitHub org/user name.
  projectName: 'open-admin', // Usually your repo name.

  onBrokenLinks: 'warn',

  // Control whether URLs have trailing slashes.
  // For GitHub pages, it's recommended to set this to true
  trailingSlash: true,

  // Set the favicon of your site
  favicon: 'img/logo.svg',



  presets: [
    [
      'classic',
      /** @type {import('@docusaurus/preset-classic').Options} */
      ({
        docs: {
          sidebarPath: './sidebars.js',
          // Please change this to your repo.
          // Remove this to remove the "edit this page" links.
          editUrl:
              'https://github.com/jiangood/open-admin/tree/main/site/',
          // Show last update time
          showLastUpdateTime: true,
          // Show last update author
          showLastUpdateAuthor: true,
          // Route base path for docs
          routeBasePath: 'docs',
          // Include README.md as the homepage
          includeCurrentVersion: true,
        },

        theme: {
          customCss: './src/css/custom.css',
        },
      }),
    ],
  ],

  themeConfig:
  /** @type {import('@docusaurus/preset-classic').ThemeConfig} */
      ({
        // Replace with your project's social card
        image: 'img/logo.svg',
        colorMode: {
          respectPrefersColorScheme: true,
          // Enable dark mode toggle
          defaultMode: 'light',
          disableSwitch: false,
        },
        navbar: {
          title: 'open-admin',
          logo: {
            alt: 'open-admin Logo',
            src: 'img/logo.svg',
            // Add logo for dark mode if needed
            // srcDark: 'img/logo-dark.svg',
          },
          items: [
            {
              type: 'docSidebar',
              sidebarId: 'tutorialSidebar',
              position: 'left',
              label: '文档',
            },
            {
              href: 'https://github.com/jiangood/open-admin',
              label: 'GitHub',
              position: 'right',
              // Add GitHub icon
              className: 'navbar-github-link',
            },
          ],
        },

        prism: {
          theme: prismThemes.github,
          darkTheme: prismThemes.dracula,
          // Add additional languages for syntax highlighting
          additionalLanguages: ['java', 'javascript', 'typescript', 'sql', 'yaml', 'json'],
        },
        // Enable table of contents for docs
        tableOfContents: {
          minHeadingLevel: 2,
          maxHeadingLevel: 5,
        },

      }),
};

export default config;
