/** @type {import('@docusaurus/types').Config} */
const config = {
  title: 'open-admin 文档',
  tagline: 'open-admin 项目官方文档',
  favicon: 'img/favicon.ico',
  url: 'https://jiangood.github.io',
  baseUrl: '/open-admin/',
  organizationName: 'jiangood',
  projectName: 'open-admin',
  onBrokenLinks: 'throw',
  onBrokenMarkdownLinks: 'warn',
  i18n: {
    defaultLocale: 'zh-CN',
    locales: ['zh-CN'],
  },
  presets: [
    [
      'classic',
      /** @type {import('@docusaurus/preset-classic').Options} */
      ({
        docs: {
          routeBasePath: '/',
          sidebarPath: './sidebars.js',
          editUrl: 'https://github.com/jiangood/open-admin/edit/master/docs/',
        },
        blog: {
          showReadingTime: true,
          editUrl: 'https://github.com/jiangood/open-admin/edit/master/docs/blog/',
        },
        theme: {
          customCss: ['./src/css/custom.css'],
        },
      }),
    ],
  ],
  themeConfig:
    /** @type {import('@docusaurus/preset-classic').ThemeConfig} */
    ({
      image: 'img/docusaurus-social-card.jpg',
      navbar: {
        title: 'open-admin 文档',
        logo: {
          alt: 'open-admin Logo',
          src: 'img/logo.svg',
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
          },
        ],
      },
      footer: {
        style: 'dark',
        links: [
          {
            title: '文档',
            items: [
              {
                label: '快速开始',
                to: '/quick-start',
              },
              {
                label: '架构设计',
                to: '/architecture',
              },
            ],
          },
          {
            title: '社区',
            items: [
              {
                label: 'GitHub',
                href: 'https://github.com/jiangood/open-admin',
              },
            ],
          },
        ],
        copyright: `Copyright © ${new Date().getFullYear()} open-admin 项目.`,
      },
      prism: {
        theme: require('prism-react-renderer/themes/github'),
        darkTheme: require('prism-react-renderer/themes/dracula'),
      },
    }),
};

module.exports = config;