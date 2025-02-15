import request from '@/utils/request'

// 查询队伍成员列表
export function listMembers(query) {
  return request({
    url: '/system/members/list',
    method: 'get',
    params: query
  })
}

// 查询队伍成员详细
export function getMembers(id) {
  return request({
    url: '/system/members/' + id,
    method: 'get'
  })
}

// 新增队伍成员
export function addMembers(data) {
  return request({
    url: '/system/members',
    method: 'post',
    data: data
  })
}

// 修改队伍成员
export function updateMembers(data) {
  return request({
    url: '/system/members',
    method: 'put',
    data: data
  })
}

// 删除队伍成员
export function delMembers(id) {
  return request({
    url: '/system/members/' + id,
    method: 'delete'
  })
}
