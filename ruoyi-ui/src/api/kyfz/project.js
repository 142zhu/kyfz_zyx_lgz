import request from '@/utils/request'

// 查询项目信息列表
export function listProject(query) {
  return request({
    url: '/kyfz/project/list',
    method: 'get',
    params: query
  })
}

// 查询项目信息详细
export function getProject(projectId) {
  return request({
    url: '/kyfz/project/' + projectId,
    method: 'get'
  })
}

// 新增项目信息
export function addProject(data) {
  return request({
    url: '/kyfz/project',
    method: 'post',
    data: data
  })
}

// 修改项目信息
export function updateProject(data) {
  return request({
    url: '/kyfz/project',
    method: 'put',
    data: data
  })
}

// 删除项目信息
export function delProject(projectId) {
  return request({
    url: '/kyfz/project/' + projectId,
    method: 'delete'
  })
}
