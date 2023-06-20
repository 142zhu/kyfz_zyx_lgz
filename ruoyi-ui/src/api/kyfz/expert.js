import request from '@/utils/request'

// 查询专家管理列表
export function listExpert(query) {
  return request({
    url: '/kyfz/expert/list',
    method: 'get',
    params: query
  })
}

// 查询专家管理详细
export function getExpert(expertId) {
  return request({
    url: '/kyfz/expert/' + expertId,
    method: 'get'
  })
}

// 新增专家管理
export function addExpert(data) {
  return request({
    url: '/kyfz/expert',
    method: 'post',
    data: data
  })
}

// 修改专家管理
export function updateExpert(data) {
  return request({
    url: '/kyfz/expert',
    method: 'put',
    data: data
  })
}

// 删除专家管理
export function delExpert(expertId) {
  return request({
    url: '/kyfz/expert/' + expertId,
    method: 'delete'
  })
}


// 查询专家管理详细
export function getExpertDetail(expertId) {
  return request({
    url: '/kyfz/expert/detail/' + expertId,
    method: 'get'
  })
}


// 查询专家管理详细
export function getExpertDetailByAccount(data) {
  return request({
    url: '/kyfz/expert/detailByAccount/',
    method: 'put',
    data: data
  })
}



// 修改专家管理
export function updateMarkProject(data) {
  return request({
    url: '/kyfz/expert/updateMarkProject',
    method: 'put',
    data: data
  })
}


// 修改专家管理
export function updateMarkThesis(data) {
  return request({
    url: '/kyfz/expert/updateMarkThesis ',
    method: 'put',
    data: data
  })
}


// 修改专家管理
export function updateMarkWork(data) {
  return request({
    url: '/kyfz/expert/updateMarkWork',
    method: 'put',
    data: data
  })
}


// 修改专家管理
export function updateMarkCertificate(data) {
  return request({
    url: '/kyfz/expert/updateMarkCertificate',
    method: 'put',
    data: data
  })
}

